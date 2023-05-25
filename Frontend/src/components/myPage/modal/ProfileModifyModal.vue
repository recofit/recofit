<template>
  <div
    class="modal fade"
    id="profileModifyModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5">
            <strong>프로필 수정</strong>
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
            <div class="mb-3">
              <input
                id="file"
                type="file"
                name="file"
                accept="image/*"
                multiple
                class="inputfile"
                @change="uploadImage"
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
  name: "ProfileModifyModal",
  data() {
    return {
      image: "",
      id: JSON.parse(sessionStorage.getItem("loginUser")).id,
      nickname: JSON.parse(sessionStorage.getItem("loginUser")).nickname,
      nickbool: true,
      picbool: true,
    };
  },
  computed: {
    ...mapState(["member"]),
  },
  methods: {
    uploadImage(e) {
      this.image = e.target.files[0];
    },
    modifyProfile() {
      let form = new FormData();
      form.append("pictureFile", this.image);

      if (this.member.nickname == this.nickname) {
        this.nickbool = false;
      }
      if (this.image == "") {
        this.picbool = false;
      }

      let data = {
        form: form,
        nickname: this.member.nickname,
        memberId: this.id,
        picbool: this.picbool,
        nickbool: this.nickbool,
      };

      this.$store.dispatch("editProfile", data);
      this.$router.go(0);
    },
  },
};
</script>

<style scoped>
button {
  margin: 1px;
}
</style>
