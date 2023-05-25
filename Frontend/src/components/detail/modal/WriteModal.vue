<template>
    <div class="modal fade" id="writeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">

      <div class="modal-header">
        <h1 class="modal-title fs-5"><strong>{{result.title}} 리뷰 작성</strong></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
      </div>
        
        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="mb-3">
              <input type="text" class="form-control" placeholder="제목을 작성해주세요" v-model="title" />
            </div>
            <div class="mb-3">
              <textarea class="form-control" rows="3" placeholder="내용을 작성해주세요" v-model="content" />
            </div>

            <span class="notice"><strong>{{result.title}}의 점수를 평가해주세요</strong></span>
            <div class="star-rating">
              <input type="radio" id="5-stars" name="rating" value="5" v-model="rate"/>
              <label for="5-stars" class="star">&#9733;</label>
              <input type="radio" id="4-stars" name="rating" value="4" v-model="rate"/>
              <label for="4-stars" class="star">&#9733;</label>
              <input type="radio" id="3-stars" name="rating" value="3" v-model="rate"/>
              <label for="3-stars" class="star">&#9733;</label>
              <input type="radio" id="2-stars" name="rating" value="2" v-model="rate"/>
              <label for="2-stars" class="star">&#9733;</label>
              <input type="radio" id="1-star" name="rating" value="1" v-model="rate"/>
              <label for="1-star" class="star">&#9733;</label>
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
import {mapState} from 'vuex'

export default {
  name: "WriteModal",
  data() {
    return {
      memberId: "",
      placeId: "",
      title: "",
      content: "",
      rate: "",
    }
  },
  methods: {
    writeReview() {   
      let review = {
        memberId: JSON.parse(sessionStorage.getItem("loginUser")).id,
        placeId: this.$store.state.result.title,
        title: this.title,
        content: this.content,
        rate: this.rate,
      }

      this.$store.dispatch("writeReview", review);
      this.$router.go(0);
    }
  },
  computed: {
    ...mapState(['result', 'reviews']),
  },
}
</script>

<style scoped>
button {
  margin: 1px;
}

.notice {
  justify-content: center;
  align-items: center;
  display: flex;
}

.star-rating {
  display:flex;
  flex-direction: row-reverse;
  font-size:1.5em;
  justify-content:space-around;
  padding:0 .2em;
  text-align:center;
  width:5em;
  margin: 0 auto;
}

.star-rating input {
  display:none;
}

.star-rating label {
  color:#ccc;
  cursor:pointer;
}

.star-rating :checked ~ label {
  color:#f90;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  color:#fc0;
}
</style>