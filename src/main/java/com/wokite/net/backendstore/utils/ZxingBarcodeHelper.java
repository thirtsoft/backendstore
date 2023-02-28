package com.wokite.net.backendstore.utils;

public class ZxingBarcodeHelper {

    private static final String BARCODE_PATH = "C:\\Users\\Folio9470m\\Desktop\\QRCODE_SERVER\\BarCode\\";

    /*

    public static String generatedBarCodeWithProduct(Product product) throws Exception {

        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        String barCode = BARCODE_PATH + generateCodeCommand() + ".png";


        Code128Writer writer = new Code128Writer();

        BitMatrix bitMatrix = writer.encode(generateCodeCommand() + "\n" +
                product.getReference() + "\n" + product.getDesignation() + "\n" +
                product.getPrixDetail() + "\n" + product.getQtestock(), BarcodeFormat.CODE_128, 250, 200, hintMap);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToPath(bitMatrix, "png", Paths.get(barCode));

        return "Barcode created...";
    }

    public static String readProductBarCode(String barcode) throws Exception {

        BufferedImage bf = ImageIO.read(new FileInputStream(BARCODE_PATH));

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(bf)
        ));

        Result result = new MultiFormatReader().decode(binaryBitmap);

        return result.getText();

    }

    */

}
