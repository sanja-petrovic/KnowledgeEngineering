//import  "../styles/globals.scss";
import { ConfigProvider } from "antd";
import type { AppProps } from "next/app";
import { Inter } from "next/font/google";

const inter = Inter({ subsets: ["latin"] });
export default function App({ Component, pageProps }: AppProps) {
  return (
    <ConfigProvider
      theme={{
        token: {
          fontFamily: `${inter.style.fontFamily}`,
        },
      }}
    >
      <main className={inter.className}>
        <Component {...pageProps} />
      </main>
    </ConfigProvider>
  );
}
