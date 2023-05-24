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
    places: [],
    result: {},
    results: [],
    location: { lat: 0, lng: 0 },
    review: {},
    reviews: [],
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
    },
    SEARCH_POPULAR_YOUTUBE(state, video) {
      state.videos1.push(video);
    },
    SEARCH_LIKE_YOUTUBE(state, videos) {
      state.videos2 = videos;
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
    },
    SEARCH_PLACE(state, results) {
      state.results = results;
    },
    DETAIL_PLACE(state, result) {
      state.result = result;
      router.push(`/detail`);
    },
    SEARCH_LOCATION(state, location) {
      state.location.lat = location.lat;
      state.location.lng = location.lng;
    },
    SET_REVIEW: function(state, review) {
      state.review = review;
    },
    SET_REVIEWS: function(state, reviews) {
      state.reviews = reviews;
    },
    WRITE_REVIEW: function(state, review) {
      state.reviews.push(review);
      console.log(state.reviews);
    },
    DELETE_REVIEW: function(state) {
      state.reviews
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
          router.go(0);
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
          maxResults: 2,
        },
      })
      .then((res) => {
        for (const item of res.data.items) {
          axios({
            url: "https://www.googleapis.com/youtube/v3/videos",
            method: "GET",
            params: {
              key: API_KEY,
              part: "statistics",
              id: item.id.videoId,
            },
          })
          .then((res) => {
            commit("SEARCH_POPULAR_YOUTUBE", res.data.items);
          })
          .catch((err) => console.log(err))
        }
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
          maxResults: 2,
        },
      })
      .then((res) => {
        console.log(res.data.items)
        commit("SEARCH_LIKE_YOUTUBE", res.data.items);
      })
      .catch((err) => console.log(err))
    },
    searchPlace({commit}, payload) {
      const URL = "http://api.kcisa.kr/openapi/service/rest/convergence2019/getConver09";
      const API_KEY = "55ca8ff9-ef34-47b2-a379-3c10775354e8";
      axios({
        url: URL,
        method: "GET",
        params: {
          serviceKey: API_KEY,
          keyword: payload.keyword,
          where: payload.region,
        },
      })
      .then((res) => {
        commit("SEARCH_PLACE", res.data.response.body.items.item);
      })
      .catch((err) => console.log(err));
    },
    detailPlace({commit}, payload) {
      commit("DETAIL_PLACE", payload);
    },
    searchLocation({ commit }, address) {
      let temp = address.split("").reverse().join("").toString();
      let reverse = temp;
      
      while(isNaN(temp.charAt(0))) {
        temp = temp.substr(temp.indexOf(" ") + 1)
      }
      
      temp = temp.split("").reverse().join("");

      if (temp.length > 0) {
        address = temp;
      } else {
        address = reverse.split("").reverse().join("");
      }

      axios({
        url:
          "https://maps.googleapis.com/maps/api/geocode/json?address=" +
          address +
          "&key=AIzaSyArzJTlqNuBRUSFM45tn5D-wAkj1499F2U",
        method: "GET",
      })
        .then((res) => {
          commit("SEARCH_LOCATION", res.data.results[0].geometry.location);
        })
        .catch((err) => console.log(err));
    },
    getReview: function({commit}, reviewId) {
      const API_URL = `/review/detail/` + reviewId;

      return axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(res => {
          commit("SET_REVIEW", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getReviews: function({commit}, exerciseId) {
      const API_URL = `/review/list/` + exerciseId;

      return axios({
        url: API_URL,
        method: "GET",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(res => {
          commit("SET_REVIEWS", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    writeReview: function({commit}, review) {
      const API_URL = '/review/write';
      
      axios({
        url: API_URL,
        method: "POST",
        data: review,
        headers: {},
      })
        .then(() => {
          commit("WRITE_REVIEW", review);
        })
        .catch(err => {
          console.log(err);
        });
    },
    deleteReview: function({state}, reviewId) {
      const API_URL = `/review/delete/` + reviewId;
      
      axios({
        url: API_URL,
        method: "DELETE",
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          alert("삭제 완료!");
          let index;
          for (let i = 0; i < state.reviews.length; i++) {
            if (state.reviews[i].id === reviewId) {
              index = i;
            }
          }
          state.reviews.splice(index, 1);
        })
        .catch(err => {
          console.log(err);
        });
    },
    modifyReview: function({commit}, review) {
      const API_URL = `/review/update`;

      axios({
        url: API_URL,
        method: "PUT",
        data: review,
        headers: {
          "access-token": sessionStorage.getItem("access-token"),
        },
      })
        .then(() => {
          alert("수정 완료!");
          commit;
        })
        .catch(err => {
          console.log(err);
        });
    },
  },
  modules: {},
});
