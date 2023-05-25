<template>
  <section class="information-section" id="information">
    <div class="container">
      <div class="row">
        <div class="information-img">
          <div class="img-box">
            <img :src="member.picture" />
          </div>
        </div>
        <div class="information-content">
          <div class="row">
            <div class="section-title">
              <h1>{{ member.nickname }}</h1>
              <br />
              <button
                class="edit-button"
                v-if="id == routerId"
                data-bs-toggle="modal"
                data-bs-target="#profileModifyModal"
              >
                프로필 수정
              </button>
            </div>
          </div>
          <div v-if="id == routerId">
            <button
              @click="follower"
              data-bs-toggle="modal"
              data-bs-target="#followerModal"
            >
              <b>팔로워</b></button
            >&nbsp;&nbsp;&nbsp;<a>{{ followerCnt }}</a>
            <br />
          </div>
          <div v-if="id == routerId">
            <button
              @click="following"
              data-bs-toggle="modal"
              data-bs-target="#followingModal"
            >
              <b>팔로잉</b></button
            >&nbsp;&nbsp;&nbsp;<a>{{ followingCnt }}</a> <br />
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="section-calendar">
        <h1 v-if="id == routerId">내 일정</h1>
        <h1 v-if="id != routerId">{{ member.nickname }} 일정</h1>
        <div class="reservation">
          <calendar-page />
        </div>
        <kakao-map v-if="id == routerId" />
      </div>
      <div class="section-content">
        <h1 v-if="id == routerId">내 영상</h1>
        <h1 v-if="id != routerId">{{ member.nickname }} 영상</h1>
        <div class="youtube-section">
          <my-youtube />
        </div>
      </div>
    </div>
    <follower-modal />
    <following-modal />
    <profile-modify-modal />
  </section>
</template>

<script>
import { defineComponent } from "vue";
import { mapState, mapGetters } from "vuex";
import CalendarPage from "@/views/CalendarPage.vue";
import KakaoMap from "@/components/myPage/KakaoMap.vue";
import FollowerModal from "./modal/FollowerModal.vue";
import FollowingModal from "./modal/FollowingModal.vue";
import ProfileModifyModal from "./modal/ProfileModifyModal.vue";
import MyYoutube from "@/components/myPage/MyYoutube.vue";

export default defineComponent({
  components: {
    KakaoMap,
    CalendarPage,
    FollowerModal,
    FollowingModal,
    ProfileModifyModal,
    MyYoutube,
  },
  data() {
    return {
      id: JSON.parse(sessionStorage.getItem("loginUser")).id,
      routerId: this.$route.params.id,
    };
  },
  computed: {
    ...mapState([
      "member",
      "followers",
      "followings",
      "myVideoInfo",
      "myChannel",
    ]),
    ...mapGetters(["followerCnt", "followingCnt"]),
  },
  created() {
    this.$store.dispatch("myInformation", this.routerId);
    this.follower();
    this.following();
  },
  methods: {
    follower() {
      this.$store.dispatch("getFollowers", this.routerId);
    },
    following() {
      this.$store.dispatch("getFollowings", this.routerId);
    },
    clickVideo(video) {
      this.$store.dispatch("clickVideo", video);
    },
  },
});
</script>

<style scoped>
.container {
  background-color: white;
  padding-top: 50px;
  max-width: 900px;
  margin: auto;
  box-shadow: 5px 10px 30px 10px white;
}

.reservation {
  padding-right: 100px;
  margin-bottom: 20px;
}

.row {
  display: flex;
  flex-wrap: wrap;
}

.information-img {
  width: 30%;
}

.edit-button {
  background-color: #285c4d;
  border: solid 2px;
  border-radius: 5px;
  color: white;
}
.img-box {
  border-radius: 70%;
  width: 250px;
  height: 250px;
  overflow: hidden;
}
.img-box img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.information-content {
  width: 60%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

/* section title */
.section-title {
  text-align: center;
}
.section-title h1:before {
  content: "";
  box-sizing: border-box;
  position: absolute;
  left: 0;
  bottom: -5px;
  width: 100%;
  height: 3px;
  background-color: #285c4d;
}

.section-title button {
  margin-top: 20px;
}

/* section content */
.section-content {
  flex: 0 0 100%;
  max-width: 100%;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 60px;
  padding: 0 15px;
}

.section-content h1 {
  display: inline-block;
  font-size: 40px;
  color: #000000;
  font-weight: 700;
  margin: 0;
  position: relative;
}

.section-content h1:before {
  content: "";
  box-sizing: border-box;
  position: absolute;
  left: 0;
  bottom: -5px;
  width: 100%;
  height: 3px;
  background-color: #285c4d;
}

/* section calender */
.section-calendar {
  max-width: 100%;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 60px;
  padding: 0 15px;
}
.section-calendar h1 {
  display: inline-block;
  font-size: 40px;
  color: #000000;
  font-weight: 700;
  margin-bottom: 20px;
  position: relative;
}

.section-calendar h1:before {
  content: "";
  box-sizing: border-box;
  position: absolute;
  left: 0;
  bottom: -5px;
  width: 100%;
  height: 3px;
  background-color: #285c4d;
}

.youtube-section {
  margin-top: 20px;
}

button {
  border: none;
  background-color: white;
}

.box {
  margin-top: 20px;
  display: flex;
  justify-content: space-around;
  margin: 20px;
}

.card {
  width: 45%;
}

/* Header Section */
.header {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  z-index: 99;
  background-color: #ffffff;
  transition: all 0.5s ease;
}

.header .brand-name {
  padding: 0 15px;
}
.header .brand-name a {
  text-decoration: none;
  font-size: 40px;
  color: #285c4d;
  text-transform: uppercase;
  font-weight: 800;
  letter-spacing: 2px;
  transition: all 0.5s ease;
}
.header .brand-name a:hover {
  color: #17382f;
}

.header .nav {
  float: right;
}
.header .nav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.header .nav ul li {
  display: inline-block;
  margin-left: 30px;
}
.header .nav ul li a {
  text-decoration: none;
  font-size: 16px;
  font-weight: 700;
  color: #285c4d;
  margin: 20px 0;
  display: block;
  transition: all 0.5s ease;
}
.header .nav ul li a:hover {
  color: #17382f;
}

/* Copyright Section */
.copyright {
  border-top: 1px solid #c5c5c5;
  padding: 25px 15px;
  text-align: center;
  color: #555555;
  font-size: 16px;
}
</style>
