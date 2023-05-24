<template>
  <div
    class="modal fade"
    id="FollowModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="box">
              <!-- <iframe v-if="video" width="900" height="600" :src="videoURL" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe> -->
              <iframe
                width="900"
                height="600"
                :src="videoURL"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen
              ></iframe>
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
  name: "FollowModal",
  computed: {
    ...mapState(["video"]),
    videoURL() {
      // const videoId = this.video.id;
      // return `https://www.youtube.com/embed/${videoId}`;
      return `https://www.youtube.com/embed/7TLk7pscICk`;
    },
  },
};
</script>

<style scoped>
.modal {
  width: 100%;
  height: 100%;
  object-fit: cover;
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
