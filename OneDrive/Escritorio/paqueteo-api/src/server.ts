import "dotenv/config";
import express from "express";
import * as Sentry from "@sentry/node";

Sentry.init({
  dsn: process.env.SENTRY_DSN,
  tracesSampleRate: 1.0,
});

const app = express();

app.use(express.json());

app.get("/", (_req, res) => {
  res.send("API funcionando");
});

app.get("/error", (_req, _res) => {
  throw new Error("Error de prueba Sentry 🚨");
});

Sentry.setupExpressErrorHandler(app);

const PORT = process.env.SERVER_PORT || 5000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});