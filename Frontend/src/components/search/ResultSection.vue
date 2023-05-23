<template>
<div v-if="results.length > 0">
    <div class="section-title" id="result"><h1> 검색 결과 </h1></div>
    <table class="table">
        <thead>
            <tr>
            <th scope="col">번호</th>
            <th scope="col">이름</th>
            <th scope="col">위치</th>
            </tr>
        </thead>
        
        <tbody>
            <tr v-for="(result, index) in results" :key="index" @click="detailPlace(result)">
                <td>{{index + 1}}</td>
                <td>{{result.title}}</td>
                <td>{{result.venue}}</td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: "ResultSection",
  computed: {
    ...mapState(["results"])
  },
  methods: {
        detailPlace(place) {
            this.$store.dispatch('searchLocation', place.venue);
            setTimeout(() =>this.$store.dispatch('detailPlace', place), 500);
        },
    },
}
</script>

<style scoped>
.section-title {
    margin-left: 10%;
    margin-top: 5%;
}

.view-title {
    margin-top: 0;
    margin-bottom: 2%;
}

.table {
    width: 78%; 
    margin: 0 auto;
    margin-bottom: 100px;
}

tbody tr:hover,
tbody tr:focus,
tbody tr:active {
    background-color: lightgray;
}
</style>