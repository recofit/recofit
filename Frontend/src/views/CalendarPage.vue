<template>
  <div class="calendar">
    <VCalendar expanded :attributes="attributes" />
  </div>
</template>

<script>
import { ref } from "vue";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["reservations", "reservation"]),
    computedReservations() {
      return this.reservations;
    },
    computedReservation() {
      return this.reservation;
    },
  },
  watch: {
    computedReservations(newReservations) {
      this.attributes[1].dates = newReservations;
    },
    computedReservation(newReservation) {
      console.log(this.reservation);
      this.attributes[2].dates = newReservation;
    },
  },
  data() {
    return {
      attributes: ref([
        {
          key: "today",
          highlight: {
            color: "purple",
            fillMode: "solid",
            contentClass: "italic",
          },
          dates: new Date(),
        },
        // {
        //   content: {
        //     style: {
        //       display: "none !important",
        //     },
        //   },
        //   dates: null,
        // },
        {
          content: {
            style: {
              color: "black",
            },
          },
          dot: {
            backgroundColor: "blue",
          },
          dates: null,
        },
        {
          content: {
            style: {
              color: "white",
            },
          },
          highlight: {
            color: "pink",
          },
          dates: null,
        },
      ]),
    };
  },
  created() {
    const memberId = JSON.parse(sessionStorage.getItem("loginUser")).id;

    this.$store.dispatch("getReservations", memberId);
  },
  methods: {},
};
</script>

<style scoped>
.calendar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 100px;
  margin-bottom: 20px;
}

.calendar-info {
  width: 50%;
  height: 400px;
  padding: 0px 10px 0px 10px;
  overflow: auto;
  border: 0.1px solid rgb(195, 195, 195);
  border-radius: 5px;
}
.calendar-info::-webkit-scrollbar {
  width: 10px;
  border-radius: 5px;
}
.calendar-info::-webkit-scrollbar-thumb {
  background-color: grey;
}

.calendar-box {
  font-size: 8px;
}

.card {
  padding: 10px;
  margin: 10px 0px 10px 0px;
  text-align: right;
  width: 100%;
  height: 100px;
}
</style>
