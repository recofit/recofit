<template>
  <div class="body">
    <header-section />
    <div class="container">
      <my-information />
    </div>
    <footer-section />
  </div>
</template>

<script>
import HeaderSection from "@/components/common/SimpleHeaderSection.vue";
import MyInformation from "@/components/myPage/MyInformation.vue";
import FooterSection from "@/components/common/FooterSection.vue";
export default {
  components: {
    HeaderSection,
    MyInformation,
    FooterSection,
  },
};
</script>

<style scoped>
.container {
  background-color: white;
  padding-top: 50px;
  max-width: 900px;
  margin: auto;
  box-shadow: 5px 10px 30px 10px white;
}
</style>
