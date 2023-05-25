<template>
  <div
    class="modal fade"
    id="followingModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="box">
              <div
                class="vfor"
                v-for="(following, index) in followings"
                :key="index"
              >
                <button class="wrapper" @click="movePage(following.id)">
                  <div class="picture-wrapper">
                    <img :src="`${following.picture}`" />
                  </div>
                  <div class="nickname-wrapper">
                    {{ following.nickname }}
                  </div>
                </button>
                <button class="unfollow" @click="unfollow(following.id)">
                  unfollow
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "FollowingModal",
  computed: {
    ...mapState(["followings"]),
  },
  data() {
    return {
      myId: JSON.parse(sessionStorage.getItem("loginUser")).id,
    };
  },
  methods: {
    unfollow(id) {
      let data = {
        followingId: id,
        followerId: this.myId,
      };
      this.$store.dispatch("unfollow", data);
    },
    movePage(id) {
      this.$router.push("/mypage/" + id);
      setTimeout(() => this.$router.go(0), 100);
    },
  },
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.vfor {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  margin-bottom: 20px;
  border: solid 2px;
  border-radius: 5px;
  border-color: #285c4d;
}
.wrapper {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 75%;
  height: 100px;
  background-color: white;
  border: none;
}
.picture-wrapper img {
  width: 100px;
  height: 100px;
}

.nickname-wrapper {
  width: 200px;
  font-size: 30px;
}

.modal-dialog {
  width: 30%;
}

.unfollow {
  border: solid 1px;
  border-radius: 2px;
  background-color: #285c4d;
  color: white;
}

.video-section {
  width: 80%;
  margin: 0 auto;
  margin-bottom: 5%;
}

.box {
  position: relative;
  z-index: 1;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1), 0 5px 5px 0 rgba(0, 0, 0, 0.2);
  text-align: center;
}
</style>
