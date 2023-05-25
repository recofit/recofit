<template>
  <div
    class="modal fade"
    id="followerModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div
              class="box"
              style="display: flex; justify-content: space-between"
            >
              <ol>
                <li v-for="(follower, index) in followers" :key="index">
                  {{ follower.nickname }}
                </li>
              </ol>
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
  name: "FollowerModal",
  computed: {
    ...mapState(["followers"]),
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

.modal-dialog {
  width: 30%;
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
