import styles from "../styles/fuzzy.module.scss";

const FuzzySystems = () => {
  return (
    <div className={styles.wrapper}>
      <h1>Fuzzy systems</h1>
      <div>
        <p>
          Fuzzy logic is a form of many-valued logic in which the truth value of
          variables may be any real number between 0 and 1. It is employed to
          handle the concept of partial truth, where the truth value may range
          between completely true and completely false.
        </p>
        <br />
        <p>
          Fuzzy systems are structures based on fuzzy techniques oriented
          towards information processing, where the usage of classical sets
          theory and binary logic is impossible or difficult. Their main
          characteristic involves symbolic knowledge representation in a form of
          fuzzy conditional (if-then) rules.
        </p>
        <br />
        The most well-known system is the rule based one:
        <ul>
          <li>Fuzzify all input values into fuzzy membership functions.</li>
          <li>
            Execute all applicable rules in the rulebase to compute the fuzzy
            output functions.
          </li>
          <li>
            De-fuzzify the fuzzy output functions to get "crisp" output values.
          </li>
        </ul>
      </div>
    </div>
  );
};

export default FuzzySystems;
