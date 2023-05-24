<template>
  <div>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">평점</th>
          <th scope="col">추천수</th>
        </tr>
      </thead>
    
      <tbody>
        <tr v-for="(review, index) in reviews" :key="index" data-bs-toggle="modal" data-bs-target="#detailModal" @click="detailReview(review)">
          <td>{{index + 1}}</td>
          <td>{{review.title}}</td>
          <td>{{review.name}}</td>
          <td>{{review.rate}}</td>
          <td>{{review.likeCnt}}</td>
        </tr>
      </tbody>
    </table>
    <br><br>

    <detail-modal />
    <modify-modal />
  </div>
  
</template>

<script>
import DetailModal from './modal/DetailModal.vue'
import ModifyModal from './modal/ModifyModal.vue'
import { mapState } from "vuex"

export default {
  name: "ReviewSection",
  components: { 
    DetailModal,
    ModifyModal 
  },
  methods: {
    detailReview(review) {
      this.$store.dispatch("getReview", review.id);
    },
  },
  computed: {
    ...mapState(["reviews"])
  },
  created() {
    this.$store.dispatch("getReviews", this.$route.params.title);
  }
}
</script>

<style scoped>
.table {
  width: 70%; 
  margin: 0 auto;
}
tbody tr:hover,
tbody tr:focus,
tbody tr:active {
  background-color: lightgray;
}
</style>