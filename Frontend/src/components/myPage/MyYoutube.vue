<template>
  <div class="video-section">
    <div class="box">
      <div id="popular-video" class="carousel carousel-dark slide">
        <div
          class="card"
          v-for="(video, idx) in myVideoInfo"
          :key="video.id"
          :video="video"
        >
          <img
            :src="`http://i.ytimg.com/vi/${video.id}/maxresdefault.jpg`"
            @click="clickVideo(video)"
            data-bs-toggle="modal"
            data-bs-target="#youtubeModal"
          />
          <div class="card-body">
            <span class="card-text title" style="overflow: hidden">{{
              myTitle[idx]
            }}</span
            ><br />
            <span class="card-text channel">{{ myChannel[idx] }}</span
            ><br />
          </div>
        </div>
      </div>
    </div>
  </div>
  <youtube-modal />
</template>

<script>
import { mapState } from "vuex";
import YoutubeModal from "../search/modal/YoutubeModal.vue";

export default {
  name: "MyYoutube",
  data() {
    return {
      id: JSON.parse(sessionStorage.getItem("loginUser")).id,
      routerId: this.$route.params.id,
      realIds: "",
    };
  },
  components: {
    YoutubeModal,
  },
  computed: {
    ...mapState(["myVideoInfo", "myChannel", "myTitle"]),
  },
  created() {
    this.$store.dispatch("searchMyYoutube", this.routerId);
  },
  methods: {
    clickVideo(video) {
      this.$store.dispatch("clickVideo", video);
    },
  },
};
</script>

<style scoped>
.video-section {
  width: 100%;
  margin: 0 auto;
  margin-bottom: 5%;
}

.section-title {
  margin-left: 10%;
  margin-top: 5%;
}

.view-title {
  margin-left: 11%;
  margin-bottom: 2%;
}

.title {
  color: darkslategray;
}

.carousel-item:active {
  width: 100%;
  overflow: hidden;
}

.box {
  position: relative;
  z-index: 1;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1), 0 5px 5px 0 rgba(0, 0, 0, 0.2);
  text-align: center;
  margin-bottom: 10ox;
}

.card {
  width: 45%;
  box-shadow: 8px 8px 8px rgba(0, 0, 0, 0.1);
  display: inline-block;
  margin: 2%;
  margin-top: 3%;
}

.card:hover {
  box-shadow: 8px 8px 8px rgba(0, 0, 0, 0.3);
}
.card-body {
  height: 110px;
}
.card-text {
  height: 5%;
  margin-top: -3%;
  font-weight: bold;
  text-align: left;
  line-height: 2rem;
}

.channel {
  color: #285c4d;
  margin-bottom: 1%;
}

.title {
  color: #285c4d;
  margin-bottom: 1%;
}

img {
  width: 100%;
  height: 220px;
}

.stat {
  margin-left: 20%;
}

.heart {
  margin-right: 20%;
  text-align: right;
}

.bi-heart,
.bi-heart-fill {
  font-size: 30px;
  color: rgb(204, 99, 99);
}

.color {
  color: rgb(204, 99, 99);
}
</style>
