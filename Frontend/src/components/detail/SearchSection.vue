<template>
  <div>
    <nav class="navbar">
      <div class="container-fluid">
        <div>
          <button type="button" class="btn btn-review btn-outline-primary" data-bs-toggle="modal" data-bs-target="#writeModal">리뷰작성</button>
          <button type="button" class="btn btn-review btn-outline-primary">예약하기</button>
        </div>

        <form class="d-flex">
          <button class="btn" type="button"><i class="bi bi-search"></i></button>
          <input class="form-control me-2" type="search" placeholder="제목으로 검색" aria-label="Search" size="40" />
        </form>
      </div>
    </nav>
    <hr>

    <write-modal />
  </div>
</template>

<script>
import WriteModal from './modal/WriteModal.vue'

export default {
  components: { 
    WriteModal 
  },
}
</script>

<style scoped>
.btn-review {
  color: #285c4d;
  border-color: #285c4d;
  margin-right: 5px;
}
.btn-review:hover {
  background-color: #285c4d;
  border-color: white;
  color: white;
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