import { createStore } from "vuex";
import axios from "axios";
import router from "../router";
import { createToaster } from "@meforma/vue-toaster";
const toaster = createToaster({ });

axios.defaults.baseURL = 'http://localhost:8080'

export default createStore({
  state: {
    member: {},
    members: [],
    followers: [], 
    followings: [],
    loginUser: null,
    
    video: null,
    
    videoChannel1: [],
    videoInfo1: [],
    videoState1: [],
    
    videoChannel2: [],
    videoInfo2: [],
    videoState2: [],
    myVideoInfo: [],
    myChannel: [],
    myTitle: [],
    
    likeVideos1: [],
    likeVideos2: [],

    place: {},
    places: [],
    result: {},
    results: [],
    location: { lat: 0, lng: 0 },
    
    reservation: [],
    reservations: [],
    
    review: {},
    reviews: [],
    average: 0,
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
      state.followers = followers;
    },
    GET_FOLLOWINGS: function (state, followings) {
      state.followings = followings;
    },
    SET_POPULAR_CHANNEL(state, snippet) {
      state.videoChannel1.push(snippet.channelTitle);
    },
    SET_POPULAR_INFO(state, information) {
      state.videoInfo1.push(information);
    },
    SET_POPULAR_STATE(state, flag) {
      state.videoState1.push(flag);
    },
    SET_LIKE_CHANNEL(state, snippet) {
      state.videoChannel2.push(snippet.channelTitle);
    },
    SET_LIKE_INFO(state, information) {
      state.videoInfo2.push(information);
    },
    SET_LIKE_STATE(state, flag) {
      state.videoState2.push(flag);
    },
    CLICK_VIDEO(state, video) {
      state.video = video;
    },
    SET_LIKE_VIDEOS1(state, id) {
      state.likeVideos1.push(id);
    },
    SET_LIKE_VIDEOS2(state, id) {
      state.likeVideos2.push(id);
    },
    SET_MY_YOUTUBE(state, id) {
      state.myVideoInfo.push(id)
    },
    SET_MY_CHANNEL(state, snippet) {
      state.myChannel.push(snippet.channelTitle);
      state.myTitle.push(snippet.title);
    },
    FLUSH_POPULAR(state) {
      state.videoChannel1 = [];
      state.videoInfo1 = [];
      state.videoState1 = [];
      state.likeVideos1 = [];
    },
    FLUSH_LIKE(state) {
      state.videoChannel2 = [];
      state.videoInfo2 = [];
      state.videoState2 = [];
      state.likeVideos2 = [];
    },
    FLUSH_MY(state) {
      state.myChannel = [];
      state.myVideoInfo = [];
    },
    SEARCH_PLACE(state, results) {
      state.results = results;
    },
    SAVE_PLACE(state, result) {
      state.result = result;
      router.push(`/detail/` + result.title);
    },
    SEARCH_LOCATION(state, location) {
      state.location.lat = location.lat;
      state.location.lng = location.lng;
    },
    SAVE_AVERAGE(state, average) {
      state.average = average;
    },
    SET_RESERVATIONS: function (state, reservations) {
      state.reservations = reservations;
    },
    SET_RESERVATION: function (state, reservation) {
      state.reservation = reservation;
    },
    SET_REVIEW: function(state, review) {
      state.review = review;
    },
    SET_REVIEWS: function(state, reviews) {
      state.reviews = reviews;
    },
    WRITE_REVIEW: function(state, review) {
      state.reviews.push(review);
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
          toaster.info(member.nickname + '님 회원가입을 축하드립니다!');
          setTimeout(router.go(0), 500);
        })
        .catch(() => {
          toaster.error(`회원 가입에 실패했습니다`);
        })
    },
    emailDuplicationCheck: function ({ commit }, email) {
      const API_URL = '/member/emailcheck/' + email;
      axios({
        url: API_URL,
        method: 'HEAD'
      })
        .then(() => {
          commit;
          toaster.info('사용 가능한 이메일입니다');
          toaster.info('메일 전송에 수분이 소요될 수 있습니다');
        })
        .catch(() => {
          toaster.error(`사용 불가능한 이메일입니다`);
        })
    },
    sendMessage: function ({ commit }, email) {
      const API_URL = '/member/mailsender/' + email;
      axios({
        url: API_URL,
        method: 'POST',
      })
        .then(() => {
          commit;
          toaster.info('메일이 전송되었습니다');
        })
        .catch(() => {
          toaster.error(`메일 전송에 실패했습니다`);
        })
    },
    sendContactMail: function ({ commit }, mail) {
      const API_URL = '/member/mailsender';
      axios({
        url: API_URL,
        method: 'POST',
        data: mail
      })
        .then(() => {
          commit;
          toaster.info('메일이 전송되었습니다');
        })
        .catch(() => {
          toaster.error(`메일 전송에 실패했습니다`);
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
          toaster.info('인증이 성공적으로 진행되었습니다');
        })
        .catch(() => {
          toaster.error(`인증 번호와 다릅니다`);
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
          toaster.info('사용 가능한 닉네임입니다');
        })
        .catch(() => {
          toaster.error('사용 불가능한 닉네임입니다');
        })
    },
    login: function ({ commit }, member) {
      const API_URL = '/member/login';

      axios({
        url: API_URL,
        method: 'POST',
        data: member
      })
        .then((res) => {
          commit('LOG_IN', res.data);
          toaster.info(res.data.nickname + '님 환영합니다');
          const loginData = JSON.stringify(res.data);
          sessionStorage.setItem("loginUser", loginData);
          router.push('/')
        })
        .catch(() => {
          toaster.error('로그인에 실패했습니다');
        })
    },
    kakaologin: function ({ commit }) {
      const API_URL = '/kakao/login';
      const KAKAO_URL = 'https://kauth.kakao.com/oauth/authorize'

      axios({
        url: API_URL,
        method: 'GET',
        params: { KAKAO_URL }
      })
        .then((res) => {
          commit('LOG_IN', res.data);
          toaster.info(res.data.nickname + '님 환영합니다');
          const loginData = JSON.stringify(res.data);
          sessionStorage.setItem("loginUser", loginData);
          router.push('/')
        })
        .catch(() => {
          toaster.error('로그인에 실패했습니다');
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
          toaster.info('좋은 하루 되시길 바랍니다!');
          sessionStorage.removeItem('loginUser')
          router.push('/')
        })
        .catch(() => {
          toaster.error('로그아웃에 실패했습니다');
        })
    },
    myInformation: function ({ commit }, memberId) {
      const API_URL = '/member';
      axios({
        url: API_URL,
        method: 'GET',
        params: { memberId },
      })
        .then((res) => {
          commit('MY_INFORMAITON', res.data);
        })
      // const member = JSON.parse(sessionStorage.getItem("loginUser"));
      // commit('MY_INFORMAITON', member);
    },
    getFollowers: function ({ commit }, memberId) {
      const API_URL = '/member/follower';
      axios({
        url: API_URL,
        method: 'GET',
        params: { memberId },
      })
        .then((res) => {
          let followers = res.data;
          commit('GET_FOLLOWERS', followers);
        })
        // .catch(() => {
        //   alert('내 팔로워가 이상해')
        // })
    },
    getFollowings: function ({ commit }, memberId) {
      const API_URL = '/member/following';
      axios({
        url: API_URL,
        method: 'GET',
        params: { memberId },
      })
        .then((res) => {
          let followings = res.data;
          commit('GET_FOLLOWINGS', followings);
        })
        // .catch(() => {
        //   alert('내 팔로잉이 이상해')
        // })
    },
    follow({ commit }, data) {
      const API_URL = '/member/follow/' + data.followingName;
      axios({
        url: API_URL,
        method: 'POST',
        params: {
          followerId: data.followerId
        }
      })
        .then(() => {
          commit;
          router.go(0)
        })
        .catch(() => {
          toaster.error('요청에 실패했습니다');
        })
    },
    unfollow: function ({ commit }, data) {
      const API_URL = '/member/unfollow/' + data.followingId;
      axios({
        url: API_URL,
        method: 'DELETE',
        params: {
          followerId: data.followerId
        }
      })
        .then(() => {
          commit;
          router.go(0) 
        })
        .catch(() => {
          toaster.error('요청에 실패했습니다');
        })
    },
    modifyProfile: function ({ commit }, data) {
      console.log(data.memberId)
      const API_URL = '/member/' + data.memberId;
      axios({
        url: API_URL,
        method: 'PATCH',
        data: data
      })
        .then(() => {
          commit;
          let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
          jsondata.nickname = data.nickname;
          sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
          router.go(0)
        });
    },
    uploadPicture: function ({ commit }, data) {
      const API_URL = '/member/picture/' + data.memberId;
      console.log(data.form)
      axios({
        url: API_URL,
        method: 'PATCH',
        data: data.form,
        header: { 'Content-Type': 'multipart/form-data' }
      })
        .then(() => {
          commit
          let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
          jsondata.picture = data.picture;
          sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
          router.go(0) 
        })
    },
    editProfile: function ({ commit }, data) {
      if (data.picbool) {
        let PIC_URL = '/member/picture/' + data.memberId;
        axios({
          url: PIC_URL,
          method: 'PATCH',
          data: data.form,
          header: { 'Content-Type': 'multipart/form-data' }
        })
          .then(() => {
            commit
            let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
            jsondata.picture = data.picture;
            sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
          })
          .catch((e) => {
            alert(e);
          });
      }

      // if (data.picbool && data.nickbool) {
      //   let PIC_URL = '/member/picture/' + data.memberId;
      //   axios({
      //     url: PIC_URL,
      //     method: 'PATCH',
      //     data: data.form,
      //     header: { 'Content-Type': 'multipart/form-data' }
      //   })
      //     .then(() => {
      //       commit
      //       let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
      //       jsondata.picture = data.picture;
      //       sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
      //     })
      //     .catch((e) => {
      //       alert(e);
      //     });
      //   let NICK_URL = '/member/' + data.memberId;
      //   axios({
      //     url: NICK_URL,
      //     method: 'PATCH',
      //     data: {
      //       nickname: data.nickname
      //     }
      //   })
      //     .then(() => {
      //       commit;
      //       let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
      //       jsondata.nickname = data.nickname;
      //       sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
      //       router.go(0)
      //     })
      //     .catch((e) => {
      //       alert(e);
      //     });
      // } else if (data.picbool) {
      //   let PIC_URL = '/member/picture/' + data.memberId;
      //   axios({
      //     url: PIC_URL,
      //     method: 'PATCH',
      //     data: data.form,
      //     header: { 'Content-Type': 'multipart/form-data' }
      //   })
      //     .then(() => {
      //       commit
      //       let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
      //       jsondata.picture = data.picture;
      //       sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
      //       router.go(0)
      //     })
      //     .catch((e) => {
      //       alert(e);
      //     });
      // } else if (data.nickbool) {
      //   let NICK_URL = '/member/' + data.memberId;
      //   axios({
      //     url: NICK_URL,
      //     method: 'PATCH',
      //     data: {
      //       nickname: data.nickname
      //     }
      //   })
      //     .then(() => {
      //       commit;
      //       let jsondata = JSON.parse(sessionStorage.getItem('loginUser'));
      //       jsondata.nickname = data.nickname;
      //       sessionStorage.setItem('loginUser', JSON.stringify(jsondata));
      //       router.go(0)
      //     })
      //     .catch((e) => {
      //       alert(e);
      //     });
      // }
    },
    createReservation: function({ commit }, data) {
      const API_URL = '/reservation/write';
      axios({
        url: API_URL,
        method: 'POST',
        data: data,
      })
        .then(() => {
          commit;
        })
    },
    getReservations: function ({ commit }, memberId) {
      const API_URL = '/reservation';
      axios({
        url: API_URL,
        method: 'GET',
        params: { memberId },
      })
        .then((res) => {
          let reservations = [];

          let i;

          for (i = 0; i < res.data.length; i++) {
            let start_date = res.data[i].startDate;
            let end_date = res.data[i].endDate;
            let startDate = res.data[i].startDate[0] + "년" + res.data[i].startDate[1] + "월" + res.data[i].startDate[2] + "일";
            let endDate = res.data[i].endDate[0] + "년" + res.data[i].endDate[1] + "월" + res.data[i].endDate[2] + "일";
            reservations.push({ title: res.data[i].title, venue: res.data[i].venue, startDate: startDate, endDate: endDate, start: new Date(start_date[0], start_date[1] - 1, start_date[2]), end: new Date(end_date[0], end_date[1] - 1, end_date[2]) });
          }

          commit('SET_RESERVATIONS', reservations);
        })
    },
    getReservationsByName: function ({ commit }, placeName) {
      const API_URL = '/reservation/' + placeName;
      axios({
        url: API_URL,
        method: 'GET',
        params: { placeName },
      })
        .then((res) => {
          let reservations = [];
          let i;

          for (i = 0; i < res.data.length; i++) {
            let start_date = res.data[i].startDate;
            let end_date = res.data[i].endDate;
            reservations.push({ start: new Date(start_date[0], start_date[1] - 1, start_date[2]), end: new Date(end_date[0], end_date[1] - 1, end_date[2]) });
          }

          commit('SET_RESERVATIONS', reservations);
        })
    },
    clickedDate({ commit }, data) {
      let reservation = [];
      reservation.push(data)
      commit('SET_RESERVATION', reservation);
    },
    searchPopularYoutube({commit}, payload) {
      commit('FLUSH_POPULAR');

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
          maxResults: 1,
        },
      })
      .then((res) => {
        const API_URL = '/video/list';

        axios({
          url: API_URL,
          method: "GET",
          params: {
            memberId: JSON.parse(sessionStorage.getItem("loginUser")).nickname,
          },
        })
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              commit("SET_LIKE_VIDEOS1", res.data[i].id);
            }
          })
          .catch(err => {
            console.log(err);
          });

        for (const item of res.data.items) {
          commit("SET_POPULAR_CHANNEL", item.snippet);

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
            commit("SET_POPULAR_INFO", res.data.items[0]);
            
            let flag = false;
            for (let i = 0; i < this.likeVideos1.length; i++) {
              if (res.data.items[0].id === this.likeVideos1[i]) {
                flag = true;
              }
            }

            commit("SET_POPULAR_STATE", flag);
          })
          .catch((err) => console.log(err))
        }
      })
      .catch((err) => console.log(err));
    },
    searchLikeYoutube({commit}, payload) {
      commit('FLUSH_LIKE');

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
          maxResults: 1,
        },
      })
      .then((res) => {
        const API_URL = '/video/list';

        axios({
          url: API_URL,
          method: "GET",
          params: {
            memberId: JSON.parse(sessionStorage.getItem("loginUser")).nickname,
          },
        })
          .then((res) => {
            for (let i = 0; i < res.data.length; i++) {
              commit("SET_LIKE_VIDEOS2", res.data[i].id);
            }
          })
          .catch(err => {
            console.log(err);
          });

        for (const item of res.data.items) {
          commit("SET_LIKE_CHANNEL", item.snippet);

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
            commit("SET_LIKE_INFO", res.data.items[0]);

            let flag = false;
            for (let i = 0; i < this.likeVideos2.length; i++) {
              if (res.data.items[0].id === this.likeVideos2[i]) {
                flag = true;
              }
            }
            commit("SET_LIKE_STATE", flag);
          })
          .catch((err) => console.log(err))
        }
      })
      .catch((err) => console.log(err));
    },
    searchMyYoutube({ commit }, memberId) {
      commit('FLUSH_MY');
      
      const API_URL = '/video/list';

      axios({
        url: API_URL,
        method: "GET",
        params: {
          memberId: memberId,
        },
      })
        .then((res) => {
          console.log(res.data.length)
          for (let i = 0; i < res.data.length; i++) {
            let data = res.data[i];
            commit("SET_MY_YOUTUBE", data);

            // const URL = "https://www.googleapis.com/youtube/v3/search";
            // const API_KEY = "";
            // axios({
            //   url: URL,
            //   method: "GET",
            //   params: {
            //     key: API_KEY,
            //     part: "snippet",
            //     order: "viewCount",
            //     videoCategoryId: 17,
            //     q: data,
            //     type: "video",
            //     maxResults: 2,
            //   },
            // })
            //   .then((res) => {
            //     for (let item in res.data.items) {
            //       commit("SET_MY_CHANNEL", res.data.items[item].snippet);
            //       console.log(res.data.items[item].snippet)
            //     }
            //   })
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    clickVideo({commit}, payload) {
      commit("CLICK_VIDEO", payload);
    },
    doSubscribe({commit}, payload) {
      const API_URL = '/video';

      axios({
        url: API_URL,
        method: "POST",
        data: {
          memberId: JSON.parse(sessionStorage.getItem("loginUser")).id,
          videoId: payload.video.id,
          channelName: payload.channelName,
          viewCnt: payload.video.statistics.viewCount,
          likeCnt: payload.video.statistics.likeCount,
        },
      })
        .then(() => {
          commit;
        })
        .catch(err => {
          console.log(err);
        });
    },
    doUnsubscribe({commit}, payload) {
      const API_URL = '/video';

      axios({
        url: API_URL,
        method: "DELETE",
        data: {
          memberId: JSON.parse(sessionStorage.getItem("loginUser")).id,
          videoId: payload.id,
        },
      })
        .then(() => {
          commit;
        })
        .catch(err => {
          console.log(err);
        });
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
    savePlace({commit}, payload) {
      const API_URL = '/place';

      axios({
        url: API_URL,
        method: "POST",
        data: payload,
      })
        .then(() => {
          commit("SAVE_PLACE", payload);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getResult: function({commit}, title) {
      const API_URL = `/place/` + title;

      axios({
        url: API_URL,
        method: "GET",
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
      })
        .then(res => {
          let address = res.data.venue;

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

          commit("SAVE_AVERAGE", res.data.rate);
          commit("SAVE_PLACE", res.data);
    
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
        })
        .catch(err => {
          console.log(err);
        });
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
      const API_URL = `/review/` + reviewId;

      return axios({
        url: API_URL,
        method: "GET",
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
      })
        .then(res => {
          commit("SET_REVIEW", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    getReviews: function({commit}, placeName) {
      const API_URL = `/review/list/` + placeName;

      return axios({
        url: API_URL,
        method: "GET",
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
      })
        .then(res => {
          commit("SET_REVIEWS", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    searchReviews: function({commit}, information) {
      const API_URL = `/review/search/` + information.place;

      return axios({
        url: API_URL,
        method: "GET",
        params: {
          title: information.key,
        },
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
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
      })
        .then(() => {
          toaster.info(`리뷰 등록을 완료했습니다`);
          commit("WRITE_REVIEW", review);
        })
        .catch(err => {
          toaster.error(`리뷰 등록에 실패했습니다`);
          console.log(err);
        });
    },
    deleteReview: function({state}, reviewId) {
      const API_URL = `/review/` + reviewId;
      
      axios({
        url: API_URL,
        method: "DELETE",
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
      })
        .then(() => {
          toaster.info(`리뷰 삭제를 완료했습니다`);

          let index;
          for (let i = 0; i < state.reviews.length; i++) {
            if (state.reviews[i].id === reviewId) {
              index = i;
            }
          }
          state.reviews.splice(index, 1);
        })
        .catch(() => {
          toaster.error(`리뷰 삭제에 실패했습니다`);
        });
    },
    modifyReview: function({commit}, review) {
      const API_URL = `/review/` + review.id;

      axios({
        url: API_URL,
        method: "PUT",
        data: review,
        // headers: {
        //   "access-token": sessionStorage.getItem("access-token"),
        // },
      })
        .then(() => {
          toaster.info(`리뷰 수정을 완료했습니다`);
          commit;
        })
        .catch(err => {
          toaster.error(`리뷰 수정에 실패했습니다`);
          console.log(err);
        });
    },
  },
  modules: {},
});
  