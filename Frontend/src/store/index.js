import { createStore } from "vuex";
import axios from "axios";
import router from "../router";

export default createStore({
  state: {
    member: {},
    members: [],
    loginUser: null,
    video: null,
    videos1: [],
    videos2: [],
  },
  getters: {
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
      const API_KEY = "AIzaSyBH872nJMrMtQ1WkEI-dwrg6Zz0sty1Krs";
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
      const API_KEY = "AIzaSyBH872nJMrMtQ1WkEI-dwrg6Zz0sty1Krs";
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
  },
  modules: {},
});
