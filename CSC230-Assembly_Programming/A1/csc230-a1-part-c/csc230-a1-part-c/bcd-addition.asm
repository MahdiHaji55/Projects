; bcd-addition.asm
; CSC 230: Fall 2022
;
; Code provided for Assignment #1
;
; Mike Zastre (2022-Sept-22)

; This skeleton of an assembly-language program is provided to help you
; begin with the programming task for A#1, part (c). In this and other
; files provided through the semester, you will see lines of code
; indicating "DO NOT TOUCH" sections. You are *not* to modify the
; lines within these sections. The only exceptions are for specific
; changes announced on conneX or in written permission from the course
; instructor. *** Unapproved changes could result in incorrect code
; execution during assignment evaluation, along with an assignment grade
; of zero. ****
;
; In a more positive vein, you are expected to place your code with the
; area marked "STUDENT CODE" sections.

; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
; Your task: Two packed-BCD numbers are provided in R16
; and R17. You are to add the two numbers together, such
; the the rightmost two BCD "digits" are stored in R25
; while the carry value (0 or 1) is stored R24.
;
; For example, we know that 94 + 9 equals 103. If
; the digits are encoded as BCD, we would have
;   *  0x94 in R16
;   *  0x09 in R17
; with the result of the addition being:
;   * 0x03 in R25
;   * 0x01 in R24
;
; Similarly, we know than 35 + 49 equals 84. If 
; the digits are encoded as BCD, we would have
;   * 0x35 in R16
;   * 0x49 in R17
; with the result of the addition being:
;   * 0x84 in R25
;   * 0x00 in R24
;

; ANY SIGNIFICANT IDEAS YOU FIND ON THE WEB THAT HAVE HELPED
; YOU DEVELOP YOUR SOLUTION MUST BE CITED AS A COMMENT (THAT
; IS, WHAT THE IDEA IS, PLUS THE URL).



    .cseg
    .org 0

	; Some test cases below for you to try. And as usual
	; your solution is expected to work with values other
	; than those provided here.
	;
	; Your code will always be tested with legal BCD
	; values in r16 and r17 (i.e. no need for error checking).

	; 94 + 9 = 03, carry = 1
	ldi r16, 0x94
	ldi r17, 0x09

	; 86 + 79 = 65, carry = 1
	;ldi r16, 0x86
	;ldi r17, 0x79

	; 35 + 49 = 84, carry = 0
	;ldi r16, 0x35
	;ldi r17, 0x49

	; 32 + 41 = 73, carry = 0
	;ldi r16, 0x32
	;ldi r17, 0x41

; ==== END OF "DO NOT TOUCH" SECTION ==========

; **** BEGINNING OF "STUDENT CODE" SECTION **** 

; Converting r16
mov r18, r16    ; Copy r16 to r18 (preserve original value)
swap r18        ; Swap nibbles, move tens place to lower nibble
andi r18, 0x0F  ; Mask upper nibble, isolate tens digit

; Convert tens digit to its 10's equivalent and store in r19
ldi r19, 0x00   ; Clear r19 to accumulate 10's equivalent
Tens_Loop_r16:
    subi r18, 0x01 ; Decrement tens counter
    brmi Finish_Tens_r16 ; If negative, finish loop
    subi r19, -10  ; Add 10 to r19 (equivalent to `+10` but subi allows immediate)
    rjmp Tens_Loop_r16 ; Repeat
Finish_Tens_r16:

andi r16, 0x0F   ; Isolate unit digit in r16
add r16, r19     ; Add 10's equivalent to unit digit

; Converting r17
mov r18, r17    ; Copy r17 to r18
swap r18        ; Swap nibbles, move tens place to lower nibble
andi r18, 0x0F  ; Mask upper nibble, isolate tens digit

; Convert tens digit to its 10's equivalent and store in r19
ldi r19, 0x00   ; Clear r19 to accumulate 10's equivalent
Tens_Loop_r17:
    subi r18, 0x01 ; Decrement tens counter
    brmi Finish_Tens_r17 ; If negative, finish loop
    subi r19, -10  ; Add 10 to r19
    rjmp Tens_Loop_r17 ; Repeat
Finish_Tens_r17:

andi r17, 0x0F   ; Isolate unit digit in r17
add r17, r19     ; Add 10's equivalent to unit digit

add r25, r16 ; Add r16 to r25
add r25, r17 ; Add r17 to r25

; Compare r25 with 0x64
cpi r25, 0x64

; If r25 <= 0x64, skip the addition and subtraction
brlo ExtractTens ; BRLO (Branch if Lower) will jump if r25 < 0x64

; If r25 > 0x64, increment r24 and subtract 0x64 from r25
inc r24 ; increment r24
subi r25, 0x64 ; subtract 0x64 from r25

; Extract tens
ExtractTens:
    subi r25, 10 ; subtract 10
    brmi FinishTens ; if result is negative, finish loop
    inc r26 ; increment tens count
    rjmp ExtractTens ; repeat

FinishTens:
    subi r25, 0xF6 ; add back the subtracted 10 that caused exit from the loop

; Extract units (r25 is already holding the units digit after the above steps)
mov r27, r25

; Combine tens and units into BCD in r25
mov r25, r26 ; move tens into r25
swap r25 ; swap to make room for units in lower nibble
andi r27, 0x0F ; ensure r27 is clean on the high nibble
or r25, r27 ; combine them
rjmp clear_registers

; clear and free registers 
clear_registers:
	clr r26
	clr r27
	clr r18
	clr r19
	rjmp bcd_addition_end	










; **** END OF "STUDENT CODE" SECTION ********** 

; ==== BEGINNING OF "DO NOT TOUCH" SECTION ====
bcd_addition_end:
	rjmp bcd_addition_end



; ==== END OF "DO NOT TOUCH" SECTION ==========
