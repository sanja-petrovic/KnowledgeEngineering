import BayesianNetwork from "@/features/bayes/components/BayesianNetwork";
import Head from "next/head";

export default function BayesianNetworkPagePage() {
  return (
    <>
      <Head>
        <title>Bayesian network | Knowledge Engineering</title>
      </Head>
      <BayesianNetwork />
    </>
  );
}
