<template>
  <VCalendar :attributes="attributes" :disabled-dates="computedReservations" />
</template>

<script>
import { ref } from "vue";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["reservations"]),
    computedReservations() {
      return this.reservations;
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
      ]),
    };
  },
  created() {
    this.$store.dispatch("setDays");
  },
};
</script>
