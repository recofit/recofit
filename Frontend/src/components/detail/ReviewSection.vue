<template>
  <div>
    <table class="table">
      <thead>
        <tr>
          <th scope="col">번호</th>
          <th scope="col">제목</th>
          <th scope="col">작성자</th>
          <th scope="col">평점</th>
          <th scope="col">추천수</th>
        </tr>
      </thead>
    
      <tbody>
        <tr data-bs-toggle="modal" data-bs-target="#detailModal">
          <td>더미</td>
          <td>더미</td>
          <td> </td>
          <td> / 5</td>
          <td> 누구세요</td>
        </tr>
      </tbody>
    </table>
    <br><br>

    <detail-modal />
    <modify-modal />
  </div>
  
</template>

<script>
import DetailModal from './modal/DetailModal.vue'
import ModifyModal from './modal/ModifyModal.vue'
// import { mapGetters } from "vuex"

export default {
  name: "ReviewSection",
  components: { 
    DetailModal,
    ModifyModal 
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