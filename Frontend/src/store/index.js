import { createStore } from "vuex";
import axios from "axios";
import router from "../router";

export default createStore({
  state: {
    member: {},
    members: [],
    followers: [], 
    followings: [],
    loginUser: null,
    video: null,
    videos1: [],
    videos2: [],
    place: {},
    places: []
  },
  getters: {
    followerCnt: function (state) {
      return state.followers.length;
    },
    followingCnt: function (state) {
      return state.followings.length;
    },
    placeCnt: function (state) {
      return state.places.length;
    }
  },
  mutations: {
    SIGN_UP: function (state, member) {
      state.members.push(member)
    },
    LOG_IN: function (state, member) {
      state.loginUser = member
    },
    SEARCH_POPULAR_YOUTUBE(state, videos) {
      state.videos2 = videos;
      console.log(state.videos1);
    },
    SEARCH_LIKE_YOUTUBE(state, videos) {
      state.videos1 = videos;
      console.log(state.videos2);
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
    },
    MY_INFORMAITON: function (state, member) {
      state.member = member;
    },
    GET_FOLLOWERS: function (state, followers) {
      state.followers = followers
    },
    GET_FOLLOWINGS: function (state, followings) {
      state.followings = followings;
    },
    SET_PLACES: function (state, places) {
      state.places = places;
    }
  },
  actions: {
    signup: function ({ commit }, member) {
      const API_URL = '/member/signup';
      axios({
        url: API_URL,
        method: 'POST',
        data: member
      })
        .then(() => {
          commit;
          alert(member.nickname + '님 회원가입을 축하드립니다!');
        })
        .catch(() => {
          alert('회원 가입에 실패했습니다.');
        })
    },
    emailDuplicationCheck: function ({ commit }, email) {
      const API_URL = '/member/emailcheck/' + email;
      axios({
        url: API_URL,
        method: 'HEAD'
      })
        .then(() => {
          commit
          alert('사용 가능한 이메일입니다.');
          alert('메일 전송에 수분이 소요될 수 있습니다.');
        })
        .catch(() => {
          alert('사용 불가능한 이메일입니다.');
        })
    },
    sendMessage: function ({ commit }, email) {
      const API_URL = '/member/mailsender';
      axios({
        url: API_URL,
        method: 'POST',
        params: {
          email: email
        }
      })
        .then(() => {
          commit;
          alert('메일이 전송되었습니다.')
        })
        .catch(() => {
          alert('메일 전송에 실패했습니다.');
        })
    },
    emailVerify: function ({ commit }, code) {
      const API_URL = '/member/verification';
      axios({
        url: API_URL,
        method: 'POST',
        params: {
          code: code
        }
      })
        .then(() => {
          commit;
          alert('인증 완료!');
        })
        .catch(() => {
          alert('인증 번호와 다릅니다.');
        })
    },
    nicknameDuplicationCheck: function ({ commit }, nickname) {
      const API_URL = '/member/nicknamecheck/' + nickname;
      axios({
        url: API_URL,
        method: 'HEAD'
      })
        .then(() => {
          commit;
          alert('사용 가능한 닉네임입니다.');
        })
        .catch(() => {
          alert('사용 불가능한 닉네임입니다.');
        })
    },
    login: function ({ commit }, member) {
      console.log(member);
      const API_URL = '/member/login';
      axios({
        url: API_URL,
        method: 'POST',
        data: member
      })
        .then((res) => {
          commit('LOG_IN', member)
          alert(res.data.nickname + '님 어서오세요!')
          router.push('/')
        })
        .catch(() => {
          alert('너 누구야!')
        })
    },
    searchPopularYoutube({commit}, payload) {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const API_KEY = "";
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          order: "viewCount",
          videoCategoryId: 17,
          q: payload,
          type: "video",
          maxResults: 3,
        },
      })
      .then((res) => {
        commit("SEARCH_POPULAR_YOUTUBE", res.data.items);
      })
      .catch((err) => console.log(err));
    },
    searchLikeYoutube({commit}, payload) {
      const URL = "https://www.googleapis.com/youtube/v3/search";
      const API_KEY = "";
      axios({
        url: URL,
        method: "GET",
        params: {
          key: API_KEY,
          part: "snippet",
          videoCategoryId: 17,
          q: payload,
          type: "video",
          maxResults: 3,
        },
      })
      .then((res) => {
        commit("SEARCH_LIKE_YOUTUBE", res.data.items);
      })
      .catch((err) => console.log(err));
    },
    clickVideo({commit}, payload) {
      commit("CLICK_VIDEO", payload);
    }
    logout: function ({ commit }) {
      const API_URL = '/member/logout';
      axios({
        url: API_URL,
        method: 'POST'
      })
        .then(() => {
          commit;
          alert('안녕히가세요!')
          router.push('/')
        })
        .catch(() => {
          alert('나가지마!')
        })
    },
    myInformation: function ({ commit }) {
      const API_URL = '/member';
      axios({
        url: API_URL,
        method: 'GET'
      })
        .then((res) => {
          let member = res.data;
          commit('MY_INFORMAITON', member);
        })
        .catch(() => {
          alert('내 정보가 이상해')
        })
    },
    getFollowers: function ({ commit }) {
      const API_URL = '/member/follower';
      axios({
        url: API_URL,
        method: 'GET'
      })
        .then((res) => {
          let followers = res.data;
          commit('GET_FOLLOWERS', followers);
        })
        .catch(() => {
          alert('내 팔로워가 이상해')
        })
    },
    getFollowings: function ({ commit }) {
      const API_URL = '/member/following';
      axios({
        url: API_URL,
        method: 'GET'
      })
        .then((res) => {
          let followings = res.data;
          commit('GET_FOLLOWINGS', followings);
        })
        .catch(() => {
          alert('내 팔로잉이 이상해')
        })
    },
    unfollow: function ({ commit }, id) {
      const API_URL = '/member/unfollow/' + id;
      axios({
        url: API_URL,
        method: 'DELETE'
      })
        .then(() => {
          commit;
          router.go(0) 
        })
        .catch(() => {
          alert('넌 내꺼라 언팔 안돼')
        })
    },
    getPlaces: function ({ commit }) {
      const API_URL = '/place/list';
      axios({
        url: API_URL,
        method: 'GET'
      })
        .then((res) => {
          commit('SET_PLACES', res.data);
        })
        .catch(() => {
          alert('장소 안줘 안돼')
        })
    },
  },
  modules: {},
});
