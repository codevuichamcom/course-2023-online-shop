import { faStar } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";

export const StarList = ({ numOfStar, numOfHighLight, ...args }) => {
  const listStar = [];
  for (let i = 0; i < numOfStar; i++) {
    listStar.push(
      <FontAwesomeIcon
        icon={faStar}
        color={i < numOfHighLight ? "#fbd600" : ""}
      />
    );
  }
  return (
    <span className="ms-1 me-1" {...args}>
      {listStar}
    </span>
  );
};
