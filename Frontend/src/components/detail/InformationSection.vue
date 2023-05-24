<template>
  <div>
    <br><br><br><br><br>
    <section class="information-section" id="information">
      <div class="container">
        <div class="row">
          <div class="information-img">
            <div class="img-box">
              <div id="placeMap" class="border-box"></div>
            </div>
          </div>
          <div class="information-content">
            <div class="star-ratings">
              <div class="star-ratings-fill space-x-2 text-lg" :style="{ width: ratingToPercent + '%' }">
                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
              </div>
              <div class="star-ratings-base space-x-2 text-lg">
                <span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
              </div>
            </div>
            <div class="row">
              <div class="section-title">
                <h1 style="white-space: nowrap; font-size: 30px; "> {{result.title}} </h1>
                <button type="button" class="btn btn-review btn-outline-primary">예약하기</button>
              </div>
            </div>
            <p style="font-size: 16px;">
              <span v-if="result.venue != null"><b>주소</b><br> {{result.venue}} <br><br></span>
              <span v-if="result.subjectCategory != null"><b>카테고리</b><br> {{result.subjectCategory}} <br><br></span>
              <span v-if="result.description != null"><b>찾아오시는 길</b><br> {{result.description}} <br><br></span>
              <span v-if="result.subDescription != null"><b>기타 정보</b><br> {{result.subDescription}} <br><br></span>  
              <span v-if="result.reference != null"><b>연락처</b><br> {{result.reference}} <br><br></span>
              <span v-if="result.source != null"><b>웹사이트</b><br> {{result.source}} <br><br></span>  
            </p>
          </div>
        </div>
      </div>
    </section>
    <hr>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "InformationSection",
  data() {
    return {
      map: null,
    };
  },
  computed: {
    ...mapState(['result', 'location', 'average']),
    ratingToPercent() {
      const score = this.$store.state.average * 20;
      return score;
    }
  },
  created() {
    this.$store.dispatch("getResult", this.$route.params.title);
  },
  mounted() {
    setTimeout(() =>this.loadMap(), 500);
  },
  methods: {
    loadMap() {
      const container = document.getElementById("placeMap");
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(this.$store.state.location.lat, this.$store.state.location.lng), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.loadMarker();
    },
    loadMarker() {
      const markerPosition = new window.kakao.maps.LatLng(
        this.$store.state.location.lat,
        this.$store.state.location.lng
      );
      const marker = new window.kakao.maps.Marker({ position: markerPosition });
      marker.setMap(this.map);
    },
  },
}
</script>

<style scoped>
.star-ratings {
  color: #aaa9a9; 
  position: relative;
  unicode-bidi: bidi-override;
  width: max-content;
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
}
 
.star-ratings-fill {
  color: #fff58c;
  padding: 0;
  position: absolute;
  z-index: 1;
  display: flex;
  top: 0;
  left: 0;
  overflow: hidden;
  -webkit-text-fill-color: gold;
}
 
.star-ratings-base {
  z-index: 0;
  padding: 0;
}

.btn-review {
  background-color: #285c4d;
  border-color: white;
  color: white;
  margin-left: 2%;
  margin-bottom: 2%;
}
.btn-review:hover {
  background-color: white;
  border-color: #285c4d;
  color: #285c4d;
}
.btn-review:focus {
  outline: none;
}


.information-section {
  margin-bottom : 3%;
}

.information-section .information-img {
  flex: 0 0 35%;
  max-width: 35%;
  margin-left: 5%;
}

.information-section .information-content {
  flex: 0 0 50%;
  max-width: 50%;
  padding: 2% 2%;
  margin-left: 7%;
}
.information-section .section-title {
  font-size: 2rem;
  margin-bottom: 30px;
  padding: 0;
}
.information-section .section-title p {
  font-size: 1rem;
  color: #555555;
  line-height: 26px;
  margin: 0;
}

.information-content p {
  font-size: 1rem;
}

.section-title h1:before {
  content: "";
  box-sizing: border-box;
  position: absolute;
  left: 0;
  bottom: -7px;
  width: 30%;
  height: 3px;
  background-color: #285c4d;
}

#placeMap {
  width: 100%;
  height: 600px;
  border: solid 4px #285c4d; 
  border-radius: 20px;
}

span b {
  font-weight: 800;
}

</style>