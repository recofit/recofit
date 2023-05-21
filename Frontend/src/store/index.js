import { createStore } from "vuex";
import axios from "axios";
import router from "../router";

export default createStore({
  state: {
    member: {},
    members: [],
    loginUser: null
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
  },
  modules: {},
});
