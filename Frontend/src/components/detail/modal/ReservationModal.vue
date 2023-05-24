<template>
  <div
    class="modal fade"
    id="reservationModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">
            <strong>{{ result.title }} 예약하기</strong>
          </h1>
        </div>

        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="box">
              <VDatePicker
                v-model.range="range"
                :attributes="attributes"
                mode="Date"
              />
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <div class="d-grid gap-2 d-md-block">
            <button
              type="submit"
              class="btn btn-outline-primary"
              @click="createReservation(result.title)"
            >
              예약
            </button>
            <button
              type="button"
              class="btn btn-outline-danger"
              data-bs-dismiss="modal"
              aria-label="Close"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { ref } from "vue";

export default {
  name: "ReservationModal",
  computed: {
    ...mapState(["reservations", "result"]),
    computedReservations() {
      return this.reservations;
    },
  },
  watch: {
    computedReservations(newReservations) {
      this.attributes[1].dates = newReservations;
    },
    range: {
      handler: function () {
        console.log(this.range);
      },
      deep: true,
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
        {
          content: {
            style: {
              display: "none !important",
            },
          },
          dates: null,
        },
      ]),
    };
  },
  created() {
    this.$store.dispatch("getResult", this.result.title);
    this.$store.dispatch("getReservationsByName", this.$route.params.title);
  },
  methods: {
    createReservation(name) {
      let data = {
        placeName: name,
        start: this.range.start,
        end: this.range.end,
        memberId: JSON.parse(sessionStorage.getItem("loginUser")).id,
      };

      this.$store.dispatch("createReservation", data);
      this.$router.go(0);
    },
  },
};
</script>

<style scoped>
.box {
  display: flex;
  justify-content: space-around;
  text-align: center;
}
.title {
  margin: 40px;
}
button {
  margin: 1px;
}
</style>
