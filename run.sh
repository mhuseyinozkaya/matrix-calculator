#!/usr/bin/bash
#
cd bin/

if ! java org/matrix/matrixcalculator/MatrixCalculator; then
    echo "Uygulama başlatılamadı! Sınıf adı veya yapılandırma hatası olabilir." >&2
    exit 4
fi

