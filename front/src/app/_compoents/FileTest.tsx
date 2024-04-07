"use client";

import React, { useState } from "react";

type ImageType = {
  file: File;
  data: any;
};

export default function FileTest() {
  const [images, setImages] = useState<ImageType[]>([]);

  const handlerChangeFile = (e: any) => {
    const files = e.target.files as FileList;

    Array.from(files).forEach((file) => {
      fileRender(file);
    });

    console.log(files);
  };

  const fileRender = (file: File) => {
    // console.log(file);
    if (file.type.split("/")[0] !== "image") return;
    const reader = new FileReader();
    reader.onload = () => {
      if (reader.result) {
        setImages((prev) => [
          ...prev,
          {
            file: file,
            data: reader.result,
          },
        ]);
      }
    };
    reader.readAsDataURL(file);
  };

  const onDeleteFIle = (key: number) => {
    setImages((prev) => [...prev].filter((_, id) => id !== key));
  };

  const onUpload = () => {
    console.log(images);

    const fomData = new FormData();

    images.forEach((image) => {
      // fomData.append("files", image.data);
      fomData.append("files", image.file);
    });

    console.log(fomData.getAll("files"));

    fetch("/api/upload", {
      method: "post",
      body: fomData,
    });
  };

  return (
    <div>
      <div className="text-[2.5rem]">image-test 입니다.</div>
      <button onClick={() => onUpload()}>사진등록</button>
      <br />
      <input type="file" multiple onChange={handlerChangeFile} />

      <br />
      <br />
      <br />

      <h2>이미지 렌더링</h2>
      {images &&
        images.map((data, key) => (
          <img
            src={data.data}
            alt={data.file.name}
            key={key}
            onClick={() => onDeleteFIle(+key)}
          />
        ))}
    </div>
  );
}
