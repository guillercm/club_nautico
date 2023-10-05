import { HttpHeaders } from "@angular/common/http";

export const token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhdG9zIn0.CQOh4FOR5e17JDQDT9SI8A8DEXqNvjl3YeY6N6Mgm5C4eeY7zvv6BPVBiWeKGh8tPE0atvHU96RFqJN5q-IgbLa8ZDWSPe4i9d1TS6Jlj7isgDXcWeYwkd68squDsTFr_5pyQE2473A3kXnTjKepjKP3l1QK_LCgBYYlzhhupTgH67-4W-DVV5KXkr3VBiLpB24zbyJ_r2M6BvubryoZ4tVagXzLhWeFiS2nkCUzsAmNs8ZzCcnrozUWDorkR8_eklpa8tXM0R23_3xYB9GcdovnFK8DRogXPv7MwDIkA7sfgbf9GpvEY6Y_LPco7fXqhxI7jxcFTw8gK_G_L43UWA";

export const URL_API = "http://localhost:8085/";

export const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`
});