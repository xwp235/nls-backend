package com.nlsapi.core.business.kaptcha;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class KaptchaNoBackground extends Configurable implements BackgroundProducer {

    @Override
    public BufferedImage addBackground(BufferedImage baseImage) {
        var width = baseImage.getWidth();
        var height = baseImage.getHeight();
        BufferedImage imageWithBackground = new BufferedImage(width, height, 1);
        Graphics2D graph = (Graphics2D)imageWithBackground.getGraphics();
        graph.fill(new Rectangle2D.Double(0.0D, 0.0D, (double)width, (double)height));
        graph.drawImage(baseImage, 0, 0, null);
        return imageWithBackground;
    }

}

