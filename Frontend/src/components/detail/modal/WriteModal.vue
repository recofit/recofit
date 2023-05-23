<template>
    <div class="modal fade" id="writeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">

      <div class="modal-header">
        <h1 class="modal-title fs-5"><strong>운동영상 리뷰</strong></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
      </div>
        
        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="제목 작성" v-model="title" />
            </div>
            <div class="mb-3">
              <textarea class="form-control" rows="3" placeholder="내용 작성" v-model="content"></textarea>
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <div class="d-grid gap-2 d-md-block">
            <button type="submit" class="btn btn-outline-primary" @click="writeReview">등록</button>
            <button type="button" class="btn btn-outline-danger" data-bs-dismiss="modal">취소</button>
          </div>
        </div>
        
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WriteModal",
  data() {
    return {
      exerciseId: "",
      userId: "",
      title: "",
      content: "",
    }
  },
  methods: {
    writeReview() {   
      let review = {
        exerciseId: this.$route.params.id,
        userId: this.$store.state.user.id,
        title: this.title,
        content: this.content,
      }

      this.$store.dispatch("writeReview", review);
      this.$router.go(0);
    }
  },
}
</script>

<style scoped>
button {
  margin: 1px;
}
</style>