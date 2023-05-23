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
            </div>
          </div>
          <div>
            <b>팔로워 </b>&nbsp;&nbsp;&nbsp;<a>{{ followerCnt }}</a> <br />
            <ol>
              <li v-for="(follower, index) in followers" :key="index">
                {{ follower.nickname }}
              </li>
            </ol>
          </div>
          <div>
            <b>팔로잉 </b>&nbsp;&nbsp;&nbsp;<a>{{ followingCnt }}</a> <br />
            <ol>
              <li v-for="(following, index) in followings" :key="index">
                {{ following.nickname }}
                <button @click="unfollow(following.id)">팔로우 취소</button>
              </li>
            </ol>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="section-content">
        <h1>내 영상</h1>
        <div class="box">
          <div class="card">
            <iframe src="https://www.youtube.com/embed/gMaB-fG4u4g"></iframe>
            <div class="card-body">
              <h5 class="card-title">주제</h5>
              <span class="card-text">내용</span>
              <i class="bi bi-eye">조회수</i>
            </div>
          </div>
          <div class="card">
            <iframe src="https://www.youtube.com/embed/gMaB-fG4u4g"></iframe>
            <div class="card-body">
              <h5 class="card-title">주제</h5>
              <span class="card-text">내용</span>
              <i class="bi bi-eye">조회수</i>
            </div>
          </div>
        </div>
      </div>
      <div class="section-content">
        <h1>내 장소</h1>
        <google-maps></google-maps>
      </div>
    </div>
  </section>
</template>

<script>
import { defineComponent } from "vue";
import { mapState, mapGetters } from "vuex";

export default defineComponent({
  computed: {
    ...mapState(["member", "followers", "followings"]),
    ...mapGetters(["followerCnt", "followingCnt"]),
  },
  created() {
    this.$store.dispatch("myInformation");
    this.$store.dispatch("getFollowers");
    this.$store.dispatch("getFollowings");
  },
  methods: {
    unfollow(id) {
      this.$store.dispatch("unfollow", id);
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

.row {
  display: flex;
  flex-wrap: wrap;
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
  display: flex;
  justify-content: space-around;
  align-items: center;
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
