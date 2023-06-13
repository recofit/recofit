<template>
  <div class="place-container">
    <div id="map"></div>
    <div class="map-info">
      <div
        class="card"
        v-for="(reservation, index) in reservations"
        :key="index"
      >
        <div
          class="place-box"
          @click="
            getPlace(
              reservation.title,
              reservation.venue,
              reservation.start,
              reservation.end
            )
          "
        >
          <h5>{{ reservation.title }}</h5>
          {{ reservation.venue }}
          <br />
          시작 : {{ reservation.startDate }} / 끝 : {{ reservation.endDate }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "KakaoMap",
  computed: {
    ...mapState(["reservations"]),
  },
  data() {
    return {
      id: this.$route.params.id,
      map: null,
    };
  },
  created() {
    // const memberId = JSON.parse(sessionStorage.getItem("loginUser")).id;
    // this.$store.dispatch("getReservations", memberId);

    this.$store.dispatch("getReservations", this.id);
  },
  mounted() {
    this.loadMap();
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
    },
    getPlace(title, address, start, end) {
      let data = {
        start: start,
        end: end,
      };
      this.$store.dispatch("searchLocation", address);
      this.$store.dispatch("clickedDate", data);
      setTimeout(
        () =>
          this.movePointer(
            this.$store.state.location.lat,
            this.$store.state.location.lng
          ),
        250
      );
      setTimeout(
        () =>
          this.loadMarker(
            title,
            this.$store.state.location.lat,
            this.$store.state.location.lng
          ),
        250
      );
    },
    movePointer(latitude, longitude) {
      let moveLatLon = new window.kakao.maps.LatLng(latitude, longitude);
      this.map.setCenter(moveLatLon);
    },
    loadMarker(title, latitude, longitude) {
      const markerPosition = new window.kakao.maps.LatLng(latitude, longitude);
      const marker = new window.kakao.maps.Marker({ position: markerPosition });
      marker.setMap(this.map);

      let iwContent =
          '<div style="padding:5px; width:200px; height:100px; text-align: center">' +
          title +
          '<br><a href="https://map.kakao.com/link/map/여기로 가주세요!,' +
          latitude +
          "," +
          longitude +
          '" style="color:blue" target="_blank">카카오맵</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://localhost:8081/detail/' +
          title +
          '">상세</a></div>',
        iwPosition = new window.kakao.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다

      // 인포윈도우를 생성합니다
      let infowindow = new window.kakao.maps.InfoWindow({
        position: iwPosition,
        content: iwContent,
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(this.map, marker);
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
  height: 150px;
}
</style>
