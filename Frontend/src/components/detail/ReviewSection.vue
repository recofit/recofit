<template>
  <div>
    <table class="table">
      <thead>
        <tr data-bs-toggle="modal" data-bs-target="#detailModal">
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">작성일</th>
        </tr>
      </thead>
    
      <tbody>
        <tr>
          <td>더미</td>
          <td>더미</td>
          <td>더미</td>
          <td>더미</td>
        </tr>
      </tbody>
    </table>
    <br><br>

    <detail-modal />
    <modify-modal />
  </div>
  
</template>

<script>
// import DetailModal from './modal/DetailModal.vue'
// import ModifyModal from './modal/ModifyModal.vue'
// import { mapGetters } from "vuex"

export default {
  name: "ReviewSection",
  components: { 
    // DetailModal,
    // ModifyModal 
  },
}
</script>

<style scoped>
.table {
  width: 70%; 
  margin: 0 auto;
}
tbody tr:hover,
tbody tr:focus,
tbody tr:active {
  background-color: lightgray;
}
</style>