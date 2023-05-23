<template>
 <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
      
      <div class="modal-header">
        <h1 class="modal-title fs-5"><strong>운동영상 리뷰</strong></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
  
      <div class="modal-body">
        <div class="d-grid gap-2 d-md-block">
          <div class="box">
            <h2 class="title"><strong>{{review.title}}</strong></h2>
            <p>{{review.name}}</p>
            <p>{{review.regDate}}</p>
            <p>{{review.viewCnt}}</p>
            <br><hr>
            <p>{{review.content}}</p>
          </div>
        </div>
      </div>

      <div class="modal-footer" v-if="review.name === user.name">
        <div class="d-grid gap-2 d-md-block">
          <button type="button" class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#modifyModal">수정</button>
          <button type="submit" class="btn btn-outline-danger" @click="deleteReview(review.id)">삭제</button>
        </div>
      </div>
        
      </div>
    </div>
  </div> 
</template>

<script>
import {mapState, mapGetters} from "vuex"

export default {
  name: "DetailModal",
  methods: {
    deleteReview(id) {
      this.$store.dispatch("deleteReview", id);
      this.$router.go(0);
    }
  },
  computed: {
    ...mapState(["review"]),
    ...mapGetters(["user"])
  },
}
</script>

<style scoped>
.box {
  text-align: center;
}
.title {
  margin: 40px;
}
button {
  margin: 1px;
}
</style>