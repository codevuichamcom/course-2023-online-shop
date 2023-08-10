import React from "react";
import "./Avatar.css";

export const Avatar = ({ imageURL, name }) => {
  return (
    <div className="avatar">
      <img
        className="me-2"
        src={imageURL}
        alt={imageURL}
        width={24}
        height={24}
      />
      <div>{name}</div>
    </div>
  );
};
