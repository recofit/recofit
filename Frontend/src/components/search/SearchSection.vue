<template>
    <div class="d-flex">
        <select name="region" class="region-select" v-model="info.region">
            <option value="" selected>전국</option>
            <option value="서울">서울</option>
            <option value="인천">인천</option>
            <option value="대전">대전</option>
            <option value="대구">대구</option>
            <option value="부산">부산</option>
            <option value="울산">울산</option>
            <option value="광주">광주</option>
            <option value="제주">제주</option>
        </select>
        <input class="form-control me-2" type="text" aria-label="Search" size="40" v-model="info.keyword"/>
        <button class="btn" type="button" @click="search"><a href="#result"><i class="bi bi-search"></i></a></button>
    </div>
    <video :src="require(`@/assets/video/landscape.mp4`)" muted autoplay loop/>
</template>

<script>
export default {
    name: "SearchSection",
    data() {
        return {
            info : [
                { region: "" },
                { keyword: "" },
            ]
        };
    },
    methods: {
        search() {
            // this.$store.dispatch("searchLikeYoutube", this.info.keyword);
            // this.$store.dispatch("searchPopularYoutube", this.info.keyword);

            this.$store.dispatch("searchPlace", this.info);
        }
    },
}
</script>

<style scoped lang="css">
video {
    width: 100%;
    height: 100vh;
    object-fit: cover;
    z-index: -10;
}

.region-select, .region-select:focus {
    background-color:transparent;
    border: none;
    letter-spacing: 1px;
    color: #285c4d;
    font-weight: 600;
    font-size: 25px;
    margin-right: 2%;
}

select option {
    background-color: #ecf0f3;
    font-weight: 600;
}

.d-flex { 
    position: absolute; 
    top: 32%;
    left: 35%;
    width: 30%; 
    z-index: 10;
    border-bottom: 3px solid #285c4d;
    margin: 0 auto;
}

.form-control {
    background: transparent;
    border: none;
}
.form-control:focus {
    background: transparent;
    border: none;    
}

input {
    color: #285c4d;
    font-weight: 600;
    font-size: 25px;
}

input:focus {
    outline: none;
    border:none;
    color: #285c4d;
    font-weight: 600;
    font-size: 25px;
}   

.bi-search {
    color: #285c4d;
    font-weight: bolder;
    font-size: 25px;
}
</style>