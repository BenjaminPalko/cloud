/// <reference types="vite/client" />
import { env } from "process";

export const GATEWAY_URI = env.GATEWAY_URI ?? "localhost:3000";
