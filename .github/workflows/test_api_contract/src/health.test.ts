import { describe, it, expect } from "vitest";

const BASE_URL = "http://localhost:8080";

describe("GET /health - contract", () => {
  it("returns HTTP 200", async () => {
    const res = await fetch(`${BASE_URL}/health`);
    expect(res.status).toBe(200);
  });

  it("returns Content-Type application/json", async () => {
    const res = await fetch(`${BASE_URL}/health`);
    expect(res.headers.get("content-type")).toContain("application/json");
  });

  it('returns body { "status": "ok" }', async () => {
    const res = await fetch(`${BASE_URL}/health`);
    const body = await res.json();
    expect(body).toEqual({ status: "ok" });
  });
});