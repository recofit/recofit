<template>
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link
    href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
    rel="stylesheet"
  />
  <div class="body">
    <div class="main">
      <div class="container a-container" id="a-container">
        <div class="forms" id="a-form">
          <h2 class="form_title title">로그인하기</h2>
          <div class="form__icons">
            <a
              href="https://kauth.kakao.com/oauth/authorize?client_id=7276680ce0f3c0be137b878203962dfa&redirect_uri=http://localhost:8080/member/kakao/callback&response_type=code"
            >
              <img
                class="form__icon"
                :src="require(`@/assets/img/kakaoimg.png`)"
                alt=""
              />
            </a>
          </div>
          <span class="form__span">혹은 이메일로 로그인하기</span>
          <input
            class="form__input"
            type="text"
            v-model="loginEmail"
            placeholder="이메일"
          />
          <input
            class="form__input"
            type="password"
            v-model="loginPassword"
            placeholder="비밀번호"
          />
          <a class="form__link">비밀번호를 잊으셨나요?</a>
          <button class="form__button button" @click="login">로그인</button>
        </div>
      </div>
      <div class="container b-container" id="b-container">
        <div class="forms" id="b-form">
          <h2 class="form_title title">회원가입하기</h2>
          <div class="form__icons">
            <a @click="kakaologin">
              <img
                class="form__icon"
                :src="require(`@/assets/img/kakaoimg.png`)"
                alt=""
              />
            </a>
          </div>
          <span class="form__span">혹은 이메일로 가입하기</span>
          <div style="display: flex; align-items: center">
            <input
              class="form__input"
              type="text"
              v-model="signupEmail"
              placeholder="이메일"
            />
            <button
              class="duplication__box"
              @click="emailDuplicationCheckAndSendMessage"
            >
              인증메일
            </button>
          </div>
          <div style="display: flex; align-items: center">
            <input
              class="form__input"
              type="text"
              v-model="code"
              placeholder="인증번호"
            />
            <button class="duplication__box" @click="emailVerify">
              인증하기
            </button>
          </div>
          <div style="display: flex; align-items: center">
            <input
              class="form__input"
              type="text"
              v-model="signupNickname"
              placeholder="닉네임"
            />
            <button class="duplication__box" @click="nicknameDuplicationCheck">
              중복체크
            </button>
          </div>
          <input
            class="form__input_pass"
            type="password"
            v-model="signupPassword"
            placeholder="비밀번호"
          />
          <input
            class="form__input_pass"
            type="password"
            v-model="signupCheckPassword"
            placeholder="비밀번호 확인"
          />
          <button class="form__button button submit" @click="signup">
            회원가입
          </button>
        </div>
      </div>
      <div class="switch" id="switch-cnt">
        <div class="switch__circle"></div>
        <div class="switch__circle switch__circle--t"></div>
        <div class="switch__container" id="switch-c1">
          <h2 class="switch__title title">어서오세요!</h2>
          <p class="switch__description description">
            회원가입을 통해 RECOFIT과 함께해주세요
          </p>
          <button class="switch__button button switch-btn" @click="toggle">
            회원가입하러 가기
          </button>
        </div>
        <div class="switch__container is-hidden" id="switch-c2">
          <h2 class="switch__title title">다시 돌아오셔서 기뻐요!</h2>
          <p class="switch__description description">
            RECOFIT에서 제공하는 서비스를 즐겨보세요
          </p>
          <button class="switch__button button switch-btn" @click="toggle">
            로그인하러 가기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { createToaster } from "@meforma/vue-toaster";
const toaster = createToaster({ });


export default defineComponent({
  name: "SignPage",
  data() {
    return {
      signupEmail: "",
      signupPassword: "",
      signupCheckPassword: "",
      signupNickname: "",
      loginEmail: "",
      loginPassword: "",
      code: "",
    };
  },
  methods: {
    toggle() {
      const switchCtn = document.querySelector("#switch-cnt");
      const switchC1 = document.querySelector("#switch-c1");
      const switchC2 = document.querySelector("#switch-c2");
      const switchCircle = document.querySelectorAll(".switch__circle");
      const aContainer = document.querySelector("#a-container");
      const bContainer = document.querySelector("#b-container");

      switchCtn.classList.add("is-gx");

      setTimeout(function () {
        switchCtn.classList.remove("is-gx");
      }, 1500);

      switchCtn.classList.toggle("is-txr");
      switchCircle[0].classList.toggle("is-txr");
      switchCircle[1].classList.toggle("is-txr");

      switchC1.classList.toggle("is-hidden");
      switchC2.classList.toggle("is-hidden");
      aContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-txl");
      bContainer.classList.toggle("is-z200");
    },
    emailDuplicationCheckAndSendMessage() {
      this.$store.dispatch("emailDuplicationCheck", this.signupEmail);
      this.$store.dispatch("sendMessage", this.signupEmail);
    },
    emailVerify() {
      this.$store.dispatch("emailVerify", this.code);
    },
    nicknameDuplicationCheck() {
      this.$store.dispatch("nicknameDuplicationCheck", this.signupNickname);
    },
    signup() {
      if (
        this.signupEmail === "" ||
        this.signupPassword === "" ||
        this.signupNickname === ""
      ) {
        toaster.error(`모든 내용을 입력해주세요`);
        return;
      }

      if (this.signupPassword !== this.signupCheckPassword) {
        toaster.error(`비밀번호가 일치하지 않습니다`);
        return;
      }

      let member = {
        email: this.signupEmail,
        password: this.signupPassword,
        nickname: this.signupNickname,
      };

      this.$store.dispatch("signup", member);
    },
    login() {
      let member = {
        email: this.loginEmail,
        password: this.loginPassword,
      };

      this.$store.dispatch("login", member);
    },
  },
});
</script>

<style scoped>
*,
*::after,
*::before {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  user-select: none;
}

/* Generic */
.body {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "Montserrat", sans-serif;
  font-size: 12px;
  background-image: url("../assets/img/carousel1.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  /* background-color: #ecf0f3;
    color: #a0a5a8; */
}

/**/
.main {
  position: relative;
  width: 1000px;
  min-width: 1000px;
  min-height: 600px;
  height: 600px;
  padding: 25px;
  background-color: #ecf0f3;
  /* box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9; */
  border-radius: 12px;
  overflow: hidden;
}

@media (max-width: 1200px) {
  .main {
    transform: scale(0.7);
  }
}

@media (max-width: 1000px) {
  .main {
    transform: scale(0.6);
  }
}

@media (max-width: 800px) {
  .main {
    transform: scale(0.5);
  }
}

@media (max-width: 600px) {
  .main {
    transform: scale(0.4);
  }
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 600px;
  height: 100%;
  padding: 25px;
  background-color: #ecf0f3;
  transition: 1.25s;
}

.forms {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
}

.form__icon {
  object-fit: contain;
  width: 30px;
  margin: 0 5px;
  opacity: 0.5;
  transition: 0.15s;
}

.form__icon:hover {
  opacity: 1;
  transition: 0.15s;
  cursor: pointer;
}

.form__input {
  width: 265px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  font-family: "Montserrat", sans-serif;
  background-color: #ecf0f3;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}

.form__input_pass {
  width: 350px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  font-family: "Montserrat", sans-serif;
  background-color: #ecf0f3;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}

.form__input:focus {
  box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}

.duplication__box {
  width: 75px;
  height: 35px;
  border-radius: 8px;
  margin-left: 10px;
  font-weight: 525;
  font-size: 13px;
  letter-spacing: 1.15px;
  background-color: #285c4d;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
}

.form__span {
  color: #285c4d;
  font-family: "Noto Sans KR", sans-serif;
  margin-top: 30px;
  margin-bottom: 12px;
}

.form__link {
  color: #181818;
  font-size: 15px;
  margin-top: 25px;
  border-bottom: 1px solid #a0a5a8;
  line-height: 2;
}

.title {
  font-size: 25px;
  font-weight: 700;
  line-height: 3;
  color: #285c4d;
}

.description {
  font-size: 14px;
  letter-spacing: 0.25px;
  text-align: center;
  line-height: 1.6;
  color: #285c4d;
}

.button {
  width: 180px;
  height: 50px;
  border-radius: 25px;
  margin-top: 50px;
  font-weight: 700;
  font-size: 14px;
  letter-spacing: 1.15px;
  background-color: #285c4d;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
}

/**/
.a-container {
  z-index: 100;
  left: calc(100% - 600px);
}

.b-container {
  left: calc(100% - 600px);
  z-index: 0;
}

.switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 400px;
  padding: 50px;
  z-index: 200;
  transition: 1.25s;
  background-color: #ecf0f3;
  overflow: hidden;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
}

.switch__circle {
  position: absolute;
  width: 500px;
  height: 500px;
  border-radius: 50%;
  background-color: #ecf0f3;
  box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
  bottom: -60%;
  left: -60%;
  transition: 1.25s;
}

.switch__circle--t {
  top: -30%;
  left: 60%;
  width: 300px;
  height: 300px;
}

.switch__container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: absolute;
  width: 400px;
  padding: 50px 55px;
  transition: 1.25s;
}

.switch__button {
  cursor: pointer;
}

.switch__button:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
  transition: 0.25s;
}

.switch__button:active,
.switch__button:focus {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
  transition: 0.25s;
}

/**/
.is-txr {
  left: calc(100% - 400px);
  transition: 1.25s;
  transform-origin: left;
}

.is-txl {
  left: 0;
  transition: 1.25s;
  transform-origin: right;
}

.is-z200 {
  z-index: 200;
  transition: 1.25s;
}

.is-hidden {
  visibility: hidden;
  opacity: 0;
  position: absolute;
  transition: 1.25s;
}

.is-gx {
  animation: is-gx 1.25s;
}

@keyframes is-gx {
  0%,
  10%,
  100% {
    width: 400px;
  }

  30%,
  50% {
    width: 500px;
  }
}
</style>

<!-- <template>
  <div class="body">
    <header-section />
    <login-section />
    <footer-section />
  </div>
</template>

<script>
import HeaderSection from '@/components/common/HeaderSection.vue'
import LoginSection from '@/components/login/LoginSection.vue'
import FooterSection from '@/components/common/FooterSection.vue'

export default {
  components: {
    HeaderSection,
    LoginSection,
    FooterSection
  },
}
</script>

<style scoped la,
    IntroductionSectionng="css">
    @import "../css/loginPageStyle.css";
    @import "../css/mainPageStyle.css";
</style> -->
