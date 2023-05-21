<template>
    <section class="page-section" id="team">
        <div class="container">
            <div class="text-center section-title">
                <h2><strong>Development Team</strong></h2>
                <h3 class="section-subheading text-muted">
                    RECOFIT은 두 명의 개발자로부터 완성된 프로젝트입니다. <br>
                    우리는 RECOFIT을 통해 당신의 삶에 도움이 되기를 바랍니다. <br>
                    당신의 메시지로 인해 RECOFIT이 더 나은 서비스를 제공할 수 있습니다.
                </h3>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" :src="require(`@/assets/img/khj.jpg`)" alt="..." />
                        <h4><i class="bi bi-github"></i> 김호진 </h4>
                        <p class="text-muted">Frontend Leader</p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" :src="require(`@/assets/img/lsh.jpg`)" alt="..." />
                        <h4><i class="bi bi-github"></i> 이승현</h4>
                        <p class="text-muted">Backend Leader</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {

}
</script>

<style scoped lang="css">
@import "@/css/mainPageStyle.css";
</style>