<template>
  <div class="place-container">
    <div id="map"></div>
    <div class="map-info">
      <div class="card" v-for="(place, index) in places" :key="index">
        <div
          class="place-box"
          @click="movePointer(place.longitude, place.latitude)"
        >
          <h4>{{ place.name }}</h4>
          open : {{ place.openTime }} / close : {{ place.closeTime }}
          {{ place.address }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapState } from "vuex";

export default {
  name: "KakaoMap",
  computed: {
    ...mapState(["places"]),
    ...mapGetters(["placeCnt"]),
  },
  data() {
    return {
      map: null,
    };
  },
  created() {
    this.$store.dispatch("getPlaces");
  },
  mounted() {
    this.loadMap();
    setTimeout(() => this.loadMarkers(), 500);
  },
  methods: {
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 3, //지도의 레벨(확대, 축소 정도)
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.loadMarker();
    },
    loadMarker() {
      const markerPosition = new window.kakao.maps.LatLng(
        33.450701,
        126.570667
      );
      const marker = new window.kakao.maps.Marker({ position: markerPosition });
      marker.setMap(this.map);
    },
    movePointer(latitude, longitude) {
      let moveLatLon = new window.kakao.maps.LatLng(latitude, longitude);
      this.map.setCenter(moveLatLon);
    },
    loadMarkers() {
      for (let place in this.places) {
        const markerPosition = new window.kakao.maps.LatLng(
          this.places[place].longitude,
          this.places[place].latitude
        );
        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(this.map);
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.place-container {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.map-info {
  width: 50%;
  height: 400px;
  padding: 0px 10px 0px 10px;
  overflow: auto;
  border: 0.1px solid rgb(195, 195, 195);
  border-radius: 5px;
}
.map-info::-webkit-scrollbar {
  width: 10px;
  border-radius: 5px;
}
.map-info::-webkit-scrollbar-thumb {
  background-color: grey;
}

.card {
  padding: 10px;
  margin: 10px 0px 10px 0px;
  text-align: right;
  width: 100%;
  height: 100px;
}
</style>
