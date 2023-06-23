import { useRouter } from "next/router";

import NavigationLink from "../navigationLink/NavigationLink";
import styles from "./NavigationBar.module.scss";

const NavigationBar = () => {
  const router = useRouter();

  return (
    <div className={styles.wrapper}>
      <div className={styles.links}>
        <NavigationLink href="/ontology" text="Ontology" />
        <NavigationLink href="/fuzzy" text="Fuzzy systems" />
        <NavigationLink href="/bayes" text="Bayes network" />
        <NavigationLink
          href="/case-based-reasoning"
          text="Case based reasoning"
        />
      </div>
    </div>
  );
};

export default NavigationBar;
