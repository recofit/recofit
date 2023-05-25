<template>
  <div
    class="modal fade"
    id="nicknameModifyModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">
            <strong>닉네임 수정</strong>
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="close"
          ></button>
        </div>

        <div class="modal-body">
          <div class="d-grid gap-2 d-md-block">
            <div class="mb-3">
              <input
                type="text"
                class="form-control"
                placeholder="`${member.nickname}`"
                v-model="member.nickname"
              />
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <div class="d-grid gap-2 d-md-block">
            <button
              type="submit"
              class="btn btn-outline-primary"
              @click="modifyProfile"
            >
              수정
            </button>
            <button
              type="button"
              class="btn btn-outline-danger"
              data-bs-dismiss="modal"
            >
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "NicknameModifyModal",
  data() {
    return {
      id: JSON.parse(sessionStorage.getItem("loginUser")).id,
    };
  },
  methods: {
    modifyProfile() {
      let data = {
        nickname: this.member.nickname,
        memberId: this.id,
      };
      // let nickname = this.member.nickname;
      // let memberId = this.id;
      this.$store.dispatch("modifyProfile", data);
      // this.$router.go(0);
    },
  },
  computed: {
    ...mapState(["member"]),
  },
};
</script>

<style scoped>
button {
  margin: 1px;
}
</style>
