/// <reference types="vite/client" />

interface ImportMetaEnv {
  readonly VITE_GATEWAY_URI: string;
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
