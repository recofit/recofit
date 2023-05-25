<template>
  <section class="page-section" id="team">
    <div class="container">
      <div class="text-center section-title">
        <h2><strong>Development Team</strong></h2>
        <h3 class="section-subheading text-muted">
          RECOFIT은 두 명의 개발자로부터 완성된 프로젝트입니다. <br />
          우리는 RECOFIT을 통해 당신의 삶에 도움이 되기를 바랍니다. <br />
          당신의 메시지로 인해 RECOFIT이 더 나은 서비스를 제공할 수 있습니다.
        </h3>
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="team-member">
            <img
              class="mx-auto rounded-circle"
              :src="require(`@/assets/img/khj.jpg`)"
              alt="..."
            />
            <h4>
              <i class="bi bi-github" @click="movepage('borussen')"></i>
              김호진
            </h4>
            <p class="text-muted">Frontend Leader</p>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="team-member">
            <img
              class="mx-auto rounded-circle"
              :src="require(`@/assets/img/lsh.jpg`)"
              alt="..."
            />
            <h4>
              <i class="bi bi-github" @click="movepage('leverest96')"></i>
              이승현
            </h4>
            <p class="text-muted">Backend Leader</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  methods: {
    movepage(name) {
      location.href = "https://github.com/" + name;
    },
  },
};
</script>

<style scoped>
@import "@/css/mainPageStyle.css";
.page-section {
  padding: 5rem 0;
  background-color: #f3f2f1;
}

.page-section .section-title h2 {
  font-family: "Raleway", sans-serif;
  font-size: 50px;
  font-weight: 400;
  margin-top: 0;
  margin-bottom: 1.5rem;
}

.page-section h3.section-subheading,
.page-section .section-subheading.h3 {
  font-size: 1rem;
  margin-bottom: 4rem;
  line-height: 1.5rem;
}

.bg-light {
  --bs-bg-opacity: 1;
}

.team-member {
  margin-bottom: 2rem;
  text-align: center;
}

.team-member img {
  width: 16rem;
  height: 16rem;
  border: 0.5rem solid rgba(0, 0, 0, 0.1);
}

.team-member h4,
.team-member .h4 {
  margin-top: 1.5rem;
  margin-bottom: 0;
  font-size: 1.8rem;
}

.mx-auto {
  margin-right: auto !important;
  margin-left: auto !important;
}

.rounded-circle {
  border-radius: 50% !important;
}

.text-muted {
  --bs-text-opacity: 1;
  color: #6c757d !important;
}

.icon i {
  font-size: 120px;
}
</style>
