<template>
  <div class="body">
    <header-section/>
    <carousel-section/>
    <information-section/>
    <service-section/>
    <hr>
    <introduction-section/>
    <contact-section/>
    <footer-section/>
  </div>
</template>

<script>
import HeaderSection from '@/components/common/HeaderSection.vue'
import CarouselSection from '@/components/main/CarouselSection.vue'
import InformationSection from '@/components/main/InformationSection.vue'
import ServiceSection from '@/components/main/ServiceSection.vue'
import IntroductionSection from '@/components/main/IntroductionSection.vue'
import ContactSection from '@/components/main/ContactSection.vue'
import FooterSection from '@/components/common/FooterSection.vue'

export default {
  components: { 
    HeaderSection,
    CarouselSection,
    InformationSection,
    ServiceSection,
    IntroductionSection,
    ContactSection,
    FooterSection
  },
}
</script>