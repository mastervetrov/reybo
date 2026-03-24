const GATEWAY_URL = process.env.GATEWAY_URL || 'https://localhost:8765'
const BASE_PATH = process.env.BASE_PATH || '/shop'
const ASSET_PREFIX = process.env.ASSET_PREFIX || '/shop'

const nextConfig = {

  assetPrefix: ASSET_PREFIX,

  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: `${GATEWAY_URL}/api/:path*`,
      },
      {
        source: '/auth/:path*',
        destination: `${GATEWAY_URL}/api/auth/:path*`,
      },
    ];
  },

  trailingSlash: false,

  env: {
    NEXT_PUBLIC_GATEWAY_URL: GATEWAY_URL,
    NEXT_PUBLIC_BASE_PATH: BASE_PATH,
  },
};

export default nextConfig;
