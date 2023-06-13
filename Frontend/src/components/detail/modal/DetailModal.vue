<template>
  <div
    class="modal fade"
    id="detailModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">
            <strong>{{ result.title }} 리뷰 상세</strong>
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="box">
              <h2 class="title">
                <strong>{{ review.title }}</strong>
              </h2>
              <p>작성자 : {{ review.name }}</p>
              &nbsp;&nbsp;&nbsp;
              <button
                v-if="review.name != this.nickname"
                @click="follow(review.name)"
              >
                팔로우
              </button>
              <p>평점 : {{ review.rate }} / 5</p>
              <br />
              <hr />
              <p>{{ review.content }}</p>
            </div>
          </div>
        </div>

        <div class="modal-footer" v-if="review.name == userName">
          <div class="d-grid gap-2 d-md-block">
            <button
              type="button"
              class="btn btn-outline-primary"
              data-bs-toggle="modal"
              data-bs-target="#modifyModal"
            >
              수정
            </button>
            <button
              type="submit"
              class="btn btn-outline-danger"
              @click="deleteReview(review.id)"
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "DetailModal",
  data() {
    return {
      userName: "",
      nickname: JSON.parse(sessionStorage.getItem("loginUser")).nickname,
    };
  },
  methods: {
    deleteReview(id) {
      this.$store.dispatch("deleteReview", id);
      this.$router.go(0);
    },
    follow(name) {
      let data = {
        followerId: JSON.parse(sessionStorage.getItem("loginUser")).id,
        followingName: name,
      };
      this.$store.dispatch("follow", data);
    },
  },
  computed: {
    // ...mapGetters(["user"])
    ...mapState(["review", "result"]),
  },
  created() {
    this.userName = JSON.parse(sessionStorage.getItem("loginUser")).nickname;
  },
};
</script>

<style scoped>
.box {
  text-align: center;
}

.follow {
  display: flex;
  align-items: center;
}
.title {
  margin: 40px;
}
button {
  margin: 1px;
  color: white;
  margin-bottom: 20px;
  border-radius: 5px;
  background-color: #285c4d;
}
</style>
