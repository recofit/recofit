<template>
  <div>
    <nav class="navbar">
      <div class="container-fluid">
        <div>
          <button type="button" class="btn btn-review btn-outline-primary" data-bs-toggle="modal" data-bs-target="#writeModal">리뷰작성</button>
        </div>

        <form class="d-flex">
          <button class="btn" type="button" @click="search"><i class="bi bi-search"></i></button>
          <input class="form-control me-2" type="search" placeholder="제목으로 검색" aria-label="Search" size="40" v-model="info.key"/>
        </form>
      </div>
    </nav>
    <hr>

    <write-modal />
  </div>
</template>

<script>
import WriteModal from './modal/WriteModal.vue'
import { mapState } from "vuex"

export default {
  name: "SearchSeciton",
  components: { 
    WriteModal 
  },
  data() {
    return {
      info: {
        place : this.$route.params.title,
        key: "",
      }
    }
  },
  methods: {
    search() {
      this.$store.dispatch("searchReviews", this.info);
    }
  },
  computed: {
    ...mapState(["reviews"]),
  },
}
</script>

<style scoped>
.btn-review {
  background-color: #285c4d;
  border-color: white;
  color: white;
}
.btn-review:hover {
  background-color: white;
  border-color: #285c4d;
  color: #285c4d;
}
.btn-review:focus {
  outline: none;
}
.navbar {
  width: 70%; 
  margin: 0 auto;
}
.form-control {
  background-color: transparent;
}
.d-flex {
  border-bottom: 2px solid lightgray;
}
.form-control {
  border: none;
}
</style>