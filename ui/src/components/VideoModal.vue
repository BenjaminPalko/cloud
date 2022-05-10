<template>
  <h1>{{ videoInfo.filename }}</h1>
  <video :src="gateway_uri + '/video/stream/' + videoInfo.id"></video>
</template>

<script setup lang="ts">
import type { Video } from "@/types/video";
import { defineProps, ref } from "vue";
defineProps<{ videoInfo: Video }>();
const gateway_uri = ref(import.meta.env.VITE_GATEWAY_URI);
</script>

<style scoped></style>
