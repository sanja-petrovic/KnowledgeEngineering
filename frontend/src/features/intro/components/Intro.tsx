import { Divider } from "antd";
import styles from "../styles/intro.module.scss";

const Intro = () => {
  return (
    <div className={styles.wrapper}>
      <h1>Knowledge Engineering</h1>
      This is a project by senior undergraduate students at the Faculty of
      Technical Sciences, University of Novi Sad in the year 2023.
      <Divider
        style={{ marginTop: "-0.5rem" }}
        orientation="left"
        orientationMargin={0}
      >
        Features
      </Divider>
      <ul>
        <li>
          Component recommendation based on desired properties, using{" "}
          <b>ontologies</b>
        </li>
        <li>
          Component upgrade recommendation, using <b>ontologies</b>
        </li>
        <li>
          Evaluation of the suitability of a computer for different purposes,
          using <b>fuzzy systems</b>
        </li>
        <li>
          Evaluation of possible causes behind a computer malfunction based on
          the entered symptoms, using <b>Bayesian networks</b>
        </li>
        <li>
          Evaluation of top 5 computers most similar to the entered one, using{" "}
          <b>case-based reasoning</b>
        </li>
      </ul>
    </div>
  );
};

export default Intro;
