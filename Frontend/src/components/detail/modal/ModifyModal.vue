<template>
  <div class="modal fade" id="modifyModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
      
        <div class="modal-header">
          <h1 class="modal-title fs-5"><strong>{{result.title}} 리뷰 수정</strong></h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
        </div>

        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="제목" v-model="review.title">
            </div>
            <div class="mb-3">
              <textarea class="form-control" rows="3" placeholder="수정 내용 작성" v-model="review.content"></textarea>
            </div>
          </div>
        </div>
        
        <div class="modal-footer">
          <div class="d-grid gap-2 d-md-block">
            <button type="submit" class="btn btn-outline-primary" @click="modifyReview">수정</button>
            <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">취소</button>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex"

export default {
  name: "ModifyModal",
  
  methods: {
    modifyReview() {
      this.$store.dispatch("modifyReview", this.review);
      this.$router.go(0);
    }
  },
  computed: {
    ...mapState(["review", "result"])
  },
}
</script>

<style scoped>
button {
  margin: 1px;
}
</style>