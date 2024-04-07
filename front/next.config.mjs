/** @type {import('next').NextConfig} */

const nextConfig = {
  async rewrites() {
    return {
      fallback: [
        {
          source: "/api/:path*",
          destination: "http://localhost:8080/:path*",
        },
      ],
    };
  },
  trailingSlash: true,
  reactStrictMode: false,
  output: "standalone",
};
export default nextConfig;
