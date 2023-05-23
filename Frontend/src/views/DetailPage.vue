<template>
  <div class="body">
    <header-section />
    <information-section />
    <search-section />
    <review-section />
    <pagination-section />
    <footer-section />
  </div>
</template>

<script>
import HeaderSection from '@/components/common/HeaderSection.vue'
import InformationSection from '../components/detail/InformationSection.vue'
import SearchSection from '../components/detail/SearchSection.vue'
import ReviewSection from '../components/detail/ReviewSection.vue'
import PaginationSection from '../components/detail/PaginationSection.vue'
import FooterSection from '@/components/common/FooterSection.vue'

export default {
  components: { 
    HeaderSection,
    InformationSection,
    SearchSection,
    ReviewSection,
    PaginationSection,
    FooterSection,
  },
}
</script>

<style>
* {
  font-family: "Poppins", sans-serif;
}
</style>